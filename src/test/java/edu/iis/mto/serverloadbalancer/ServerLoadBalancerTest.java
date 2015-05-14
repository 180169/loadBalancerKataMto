package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Matcher;
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

        ServerLoadBalancer.balance( serverList( theServer ), vmList() );

        assertThat( theServer, hasCurrentPercentageLoad( 0 ) );
    }

    private Matcher<Server> hasCurrentPercentageLoad( double expectedLoad ) {
        return new CurrentPercetnageLoadMatcher( expectedLoad );
    }

    private Vm[] vmList() {
        return new Vm[]{};
    }

    private ServerBuilder server() {
        return new ServerBuilder();
    }

    private Server a( ServerBuilder builder ) {
        return builder.build();
    }

    private Server[] serverList( Server server ) {
        return new Server[]{ server };
    }

}
