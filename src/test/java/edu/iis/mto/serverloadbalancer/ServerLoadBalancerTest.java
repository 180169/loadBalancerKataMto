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
    public void emptyServer() {
        Server theServer = a( server().withCapacity( 10 ) );

        ServerLoadBalancer.balance( theServer, vmList() );

        assertThat( theServer, hasCurrentPercentageLoad( 0 ) );
    }

}
