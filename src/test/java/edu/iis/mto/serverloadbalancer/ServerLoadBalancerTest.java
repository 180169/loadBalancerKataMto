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

        balance( new Server[]{}, new Vm[]{} );

        assertThat( theServer.hasCurrentPercentageLoad(), equalTo( 0.0d ) );
    }

}
