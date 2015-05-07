package edu.iis.mto.serverloadbalancer;

import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static edu.iis.mto.serverloadbalancer.CurrentLoadPercentageMatcher.hasLoadPercentageOf;
import static edu.iis.mto.serverloadbalancer.ServerBuilder.server;
import static edu.iis.mto.serverloadbalancer.VmBuilder.vm;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith( Parameterized.class )
public class ServerLoadBalancerParametrizedTest extends ServerLoadBalancerBaseTest {

    // number is both the server capacity and vm size
    private int number;

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList( new Object[][]{
            { 1 }, { 2 }, { 3 }, { 4 }, { 5 },
            { 6 }, { 7 }, { 8 }, { 9 }, { 10 },
            { 50 }, { 100 }, { 150 }, { 200 }, { 250 }
        } );
    }

    public ServerLoadBalancerParametrizedTest( int number ) {
        this.number = number;
    }

    @Test
    public void balancingOneServerWithOneSlotCapacity_andOneSlotVm_fillsTheServerWithTheVm() {
        Server theServer = a( server().withCapacity( number ) );
        Vm theVm = a( vm().ofSize( number ) );
        balance( aListOfServersWith( theServer ), aListOfVmsWith( theVm ) );

        assertThat( theServer, hasLoadPercentageOf( 100.0d ) );
        assertThat( "the server should contain vm", theServer.contains( theVm ) );
    }

}
