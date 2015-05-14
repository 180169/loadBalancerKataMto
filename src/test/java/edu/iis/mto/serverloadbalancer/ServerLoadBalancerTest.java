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
