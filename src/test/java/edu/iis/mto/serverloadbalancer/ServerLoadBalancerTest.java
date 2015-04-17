package edu.iis.mto.serverloadbalancer;

import static edu.iis.mto.serverloadbalancer.CurrentPercentageLoadMatcher.hasPercentageLoadOf;
import static edu.iis.mto.serverloadbalancer.ServerLoadBalancer.balance;
import static edu.iis.mto.serverloadbalancer.ServerBuilder.server;
import static edu.iis.mto.serverloadbalancer.VmBuilder.vm;
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

    @Test
    public void balancingOneServerWithOneSlotCapacity_andOneSlotVm_fillsTheServerWithTheVm() {
        Server server = a( server().withCapacity( 1 ) );
        Vm vm = a( vm().withSize( 1 ) );

        balance( aListOfServersWith( server ), aListOfVMWith( vm ) );

        assertThat( server, hasPercentageLoadOf( 100.0d ) );
        assertThat( "the server should contain vm", server.contains( vm ) );
    }

    private Server a( ServerBuilder builder ) {
        return builder.build();
    }

    private Vm a( VmBuilder builder ) {
        return builder.build();
    }

    private Server[] aListOfServersWith( Server server ) {
        return new Server[]{ server };
    }

    private Vm[] aListOfVM() {
        return new Vm[]{};
    }

    private Vm[] aListOfVMWith( Vm vm ) {
        return new Vm[]{ vm };
    }

}
