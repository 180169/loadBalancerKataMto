package edu.iis.mto.serverloadbalancer;

import org.junit.Test;

import static edu.iis.mto.serverloadbalancer.ServerBuilder.server;
import static edu.iis.mto.serverloadbalancer.ServerLoadBalancer.balance;
import static edu.iis.mto.serverloadbalancer.VmBuilder.vm;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class ServerLoadBalancerTest {

    @Test
    public void itCompiles() {
        assertThat( true, equalTo( true ) );
    }

    @Test
    public void oneEmptyServer() {
        Server theServer = a( server().withCapacity( 1 ) );

        balance( new Server[]{}, new Vm[]{} );

        assertThat( theServer.currentPercentageLoad(), equalTo( 0.0d ) );
    }

    @Test
    public void oneServerFilledByOneVm() {
        Server theServer = a( server().withCapacity( 10 ) );
        Vm theVm = a( vm().ofSize( 10 ) );

        balance( new Server[]{ theServer }, new Vm[]{ theVm } );

        assertThat( theServer.currentPercentageLoad(), equalTo( 100.0d ) );
        assertThat( theServer.contains( theVm ), is( true ) );
    }

    @Test
    public void oneServerHalfFilledByOneVm() {
        Server theServer = a( server().withCapacity( 20 ) );
        Vm theVm = a( vm().ofSize( 10 ) );

        balance( new Server[]{ theServer }, new Vm[]{ theVm } );

        assertThat( theServer.currentPercentageLoad(), equalTo( 50.0d ) );
        assertThat( theServer.contains( theVm ), is( true ) );
    }

    @Test
    public void oneServerWithFewVms() {
        Server theServer = a( server().withCapacity( 60 ) );
        Vm firstVm = a( vm().ofSize( 20 ) );
        Vm secondVm = a( vm().ofSize( 10 ) );
        Vm thirdVm = a( vm().ofSize( 30 ) );

        balance( new Server[]{ theServer }, vmList( firstVm, secondVm, thirdVm ) );

        assertThat( theServer.currentPercentageLoad(), equalTo( 100.0d ) );
        assertThat( theServer.countOfVms(), equalTo( 3 ) );
        assertThat( theServer.contains( firstVm ), is( true ) );
        assertThat( theServer.contains( secondVm ), is( true ) );
        assertThat( theServer.contains( thirdVm ), is( true ) );

    }

    @Test
    public void twoServersOneMachineShouldBeOnLessLoaded() {
        Server firstServer = a( server().withCapacity( 100 ).withInitialLoad( 20.0d ) );
        Server secondServer = a( server().withCapacity( 50 ) );
        Vm theVm = a( vm().ofSize( 5 ) );

        balance( serverList( firstServer, secondServer ), vmList( theVm ) );

        assertThat( firstServer.currentPercentageLoad(), equalTo( 20.0d ) );
        assertThat( secondServer.currentPercentageLoad(), equalTo( 10.0d ) );
        assertThat( firstServer.contains( theVm ), is( false ) );
        assertThat( secondServer.contains( theVm ), is( true ) );
    }

    public static Server[] serverList( Server... servers ) {
        return servers;
    }

    public static Vm[] vmList( Vm... vms ) {
        return vms;
    }

    private Server a( ServerBuilder builder ) {
        return builder.build();
    }

    private Vm a( VmBuilder builder ) {
        return builder.build();
    }

}
