package edu.iis.mto.serverloadbalancer;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ServerLoadBalancerTest {

    @Test
    public void itCompiles() {
        assertThat( true, equalTo( true ) );
    }

    @Test
    public void oneEmptyServer() {
        Server theServer = a( server().withCapacity( 1 ) );
        
        ServerLoadBalancer.balance( new Server[]{}, new Vm[]{} );

        assertThat( theServer.currentPercentageLoad(), equalTo( 0.0d ) );
    }

    private ServerBuilder server() {
        return new ServerBuilder();
    }

    private Server a( ServerBuilder builder ) {
        return builder.build();
    }

}
