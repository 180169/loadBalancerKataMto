package edu.iis.mto.serverloadbalancer;

import org.junit.Test;

import static edu.iis.mto.serverloadbalancer.CurrentPercetnageLoadMatcher.hasCurrentPercentageLoad;
import static edu.iis.mto.serverloadbalancer.ServerBuilder.server;
import static edu.iis.mto.serverloadbalancer.ServerLoadBalancer.balance;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ServerLoadBalancerTest {

    @Test
    public void itCompiles() {
        assertThat( true, equalTo( true ) );
    }

    @Test
    public void emptyServer() {
        Server theServer = a( server().withCapacity( 10 ) );

        balance( serverList( theServer ), vmList() );

        assertThat( theServer, hasCurrentPercentageLoad( 0 ) );
    }

    @Test
    public void serverFilledByOneMachine() {
        Server theServer = a( server().withCapacity( 100 ) );
        Vm theVm = a( vm().ofSize( 100 ) );

        balance( serverList( theServer ), vmList( theVm ) );

        assertThat( theServer, hasCurrentPercentageLoad( 0 ) );
        assertThat( "server should contains the vm", theServer.contains( theVm ) );

    }

    private Vm[] vmList() {
        return new Vm[]{};
    }

    private Server a( ServerBuilder builder ) {
        return builder.build();
    }

    private Server[] serverList( Server server ) {
        return new Server[]{ server };
    }

}
