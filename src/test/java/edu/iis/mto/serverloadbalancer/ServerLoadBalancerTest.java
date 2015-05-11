package edu.iis.mto.serverloadbalancer;

import edu.iis.mto.serverloadBalancer.LoadBalancer;
import edu.iis.mto.serverloadBalancer.Server;
import edu.iis.mto.serverloadBalancer.Vm;
import java.util.ArrayList;
import java.util.List;
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
    public void oneEmptyServer() {
        Server theServer = a( server().withCapacity( 5 ) );

        LoadBalancer.balance( theServer, emptyListOfVms() );

        assertThat( theServer, hasPercentageLoad( 0.0d ) );
    }

    private ServerBuilder server() {
        return new ServerBuilder();

    }

    private Matcher<Server> hasPercentageLoad( double expectedLoad ) {
        return new CurrentPercentageLoadMatcher( expectedLoad );

    }

    private Server a( ServerBuilder builder ) {
        return builder.build();
    }

    private List<Vm> emptyListOfVms() {
        return new ArrayList<Vm>();
    }

}
