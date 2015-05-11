package edu.iis.mto.serverloadbalancer;

import edu.iis.mto.serverloadBalancer.Server;
import edu.iis.mto.serverloadBalancer.Vm;
import org.junit.Test;

import static edu.iis.mto.serverloadBalancer.LoadBalancer.balance;
import static edu.iis.mto.serverloadbalancer.CurrentPercentageLoadMatcher.hasPercentageLoad;
import static edu.iis.mto.serverloadbalancer.ServerBuilder.server;
import static edu.iis.mto.serverloadbalancer.VmBuilder.vm;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ServerLoadBalancerTest {

    @Test
    public void itCompiles() {
        assertThat( true, equalTo( true ) );
    }

    @Test
    public void oneEmptyServer() {
        Server theServer = a( server().withCapacity( 5 ) );

        balance( createServersTable( theServer ), emptyListOfVms() );

        assertThat( theServer, hasPercentageLoad( 0.0d ) );
    }

    @Test
    public void oneServerFilledWithOneVm() {
        Server theServer = a( server().withCapacity( 1 ) );
        Vm theVm = a( vm().withSize( 1 ) );

        balance( createServersTable( theServer ), new Vm[]{ theVm } );

        assertThat( theServer, hasPercentageLoad( 100.0d ) );
    }

    @Test
    public void oneServerPartiallyFilledWithOneVm() {
        Server theServer = a( server().withCapacity( 10 ) );
        Vm theVm = a( vm().withSize( 5 ) );

        balance( createServersTable( theServer ), new Vm[]{ theVm } );

        assertThat( theServer, hasPercentageLoad( 50.0d ) );
    }

    @Test
    public void oneServerFilledWithTwoVms() {
        Server theServer = a( server().withCapacity( 20 ) );
        Vm firstVm = a( vm().withSize( 10 ) );
        Vm secondVm = a( vm().withSize( 10 ) );

        balance( createServersTable( theServer ), createVmTable( firstVm, secondVm ) );

        assertThat( theServer, hasPercentageLoad( 100.0d ) );
        assertEquals( theServer.numberOfVms(), 2 );
        assertTrue( theServer.contains( firstVm ) );
        assertTrue( theServer.contains( secondVm ) );
    }

    private static Vm[] createVmTable( Vm... vms ) {
        return vms;
    }

    private static Server[] createServersTable( Server... servers ) {
        return servers;
    }

    private Server a( ServerBuilder builder ) {
        return builder.build();
    }

    private Vm a( VmBuilder builder ) {
        return builder.build();
    }

    private Vm[] emptyListOfVms() {
        return new Vm[]{};
    }

}
