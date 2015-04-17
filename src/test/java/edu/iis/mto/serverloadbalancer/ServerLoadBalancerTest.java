package edu.iis.mto.serverloadbalancer;

import static edu.iis.mto.serverloadbalancer.CurrentPercentageLoadMatcher.hasPercentageLoadOf;
import static edu.iis.mto.serverloadbalancer.ServerLoadBalancer.balance;
import static edu.iis.mto.serverloadbalancer.ServerBuilder.server;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

public class ServerLoadBalancerTest {

    @Test
    public void itCompiles() {
        assertThat( true, equalTo( true ) );
    }

    @Test
    public void balancingServer_oneServer_zeroVM_serverEmpty() {
        Server server = a( server().withCapacity( 1 ) );

        balance( aListOfServersWith( server ), aListOfVM() );

        assertThat( server, hasPercentageLoadOf( 0.0d ) );
    }

    private Server a( ServerBuilder builder ) {
        return builder.build();
    }

    private Server[] aListOfServersWith( Server server ) {
        return new Server[]{ server };
    }

    private Vm[] aListOfVM() {
        return new Vm[]{};
    }

}
