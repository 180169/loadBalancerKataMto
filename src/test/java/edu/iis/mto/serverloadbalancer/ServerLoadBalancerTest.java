package edu.iis.mto.serverloadbalancer;

import edu.iis.mto.serverloadBalancer.Server;
import edu.iis.mto.serverloadBalancer.Vm;
import org.junit.Test;

import static edu.iis.mto.serverloadBalancer.LoadBalancer.balance;
import static edu.iis.mto.serverloadbalancer.CurrentPercentageLoadMatcher.hasPercentageLoad;
import static edu.iis.mto.serverloadbalancer.ServerBuilder.server;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ServerLoadBalancerTest {

    @Test
    public void itCompiles() {
        assertThat( true, equalTo( true ) );
    }

    @Test
    public void oneEmptyServer() {
        Server theServer = a( server().withCapacity( 5 ) );

        balance( new Server[]{ theServer }, emptyListOfVms() );

        assertThat( theServer, hasPercentageLoad( 0.0d ) );
    }

    @Test
    public void oneServerFilledWithOneVm() {
        Server theServer = a( server().withCapacity( 1 ) );
        Vm theVm = a( vm().withSize( 1 ) );

        balance( new Server[]{ theServer }, new Vm[]{ theVm } );

        assertThat( theServer, hasPercentageLoad( 100.0d ) );
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

    private VmBuilder vm() {
        return new VmBuilder();
    }

}
