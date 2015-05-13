package edu.iis.mto.serverloadbalancer;

import org.junit.Test;

import static edu.iis.mto.serverloadbalancer.ServerBuilder.server;
import static edu.iis.mto.serverloadbalancer.ServerLoadBalancer.balance;
import static edu.iis.mto.serverloadbalancer.hasCurrentPercetageLoad.hasCurrentPercentageLoad;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class ServerLoadBalancerTest {

    @Test
    public void itCompiles() {
        assertThat( true, equalTo( true ) );
    }

    @Test
    public void emptyServerWithExpectedLoadEqual0() {
        Server theServer = a( server().withCapacity( 5 ) );

        balance( serverList( theServer ), vmList() );

        assertThat( theServer, hasCurrentPercentageLoad( 0.0d ) );
    }

    @Test
    public void serverFilledByOneMachine() {
        Server theServer = a( server().withCapacity( 10 ) );
        Vm theVm = a( vm().ofSize( 10 ) );

        balance( serverList( theServer ), vmList( theVm ) );

        assertThat( theServer, hasCurrentPercentageLoad( 100.0d ) );
        assertThat( theServer.contains( theVm ), is( true ) );
    }

    private Server a( ServerBuilder builder ) {
        return builder.build();
    }

    private Server[] serverList( Server server ) {
        return new Server[]{ server };
    }

    private Vm[] vmList() {
        return new Vm[]{};
    }

}
