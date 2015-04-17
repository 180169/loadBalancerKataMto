package edu.iis.mto.serverloadbalancer;

import org.junit.Test;

import static edu.iis.mto.serverloadbalancer.CurrentPercentageLoadMatcher.hasPercentageLoadOf;
import static edu.iis.mto.serverloadbalancer.ServerBuilder.server;
import static edu.iis.mto.serverloadbalancer.ServerLoadBalancer.balance;
import static edu.iis.mto.serverloadbalancer.ServerVmsCountMatcher.hasVmCountEqualTo;
import static edu.iis.mto.serverloadbalancer.VmBuilder.vm;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

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

    @Test
    public void balancingOneServerWithTenSlotsCapacity_andOneSlotVm_fillTheServerWithTenPercent() {
        Server server = a( server().withCapacity( 10 ) );
        Vm vm = a( vm().withSize( 1 ) );

        balance( aListOfServersWith( server ), aListOfVMWith( vm ) );

        assertThat( server, hasPercentageLoadOf( 10.0d ) );
        assertThat( "the server should contain vm", server.contains( vm ) );
    }

    @Test
    public void balancingAServerWithEnoughRoom_getsFilledWithAllVms() {
        Server server = a( server().withCapacity( 10 ) );
        Vm vm1 = a( vm().withSize( 1 ) );
        Vm vm2 = a( vm().withSize( 2 ) );

        balance( aListOfServersWith( server ), aListOfVMWith( vm1, vm2 ) );
        assertThat( server, hasPercentageLoadOf( 30.0d ) );
        assertThat( server, hasVmCountEqualTo( 2 ) );
        assertThat( "the server should contain vm", server.contains( vm1 ) );
        assertThat( "the server should contain vm", server.contains( vm2 ) );
    }

    @Test
    public void aVm_shouldBeBalanced_onLessLoadedServerFirst() {
        Server moreLoadedServer = a( server().withCapacity( 100 ).withInitialLoad( 50 ) );
        Server lessLoadedServer = a( server().withCapacity( 100 ).withInitialLoad( 25 ) );

        Vm vm = a( vm().withSize( 1 ) );

        balance( aListOfServersWith( moreLoadedServer, lessLoadedServer ), aListOfVMWith( vm ) );
        assertThat( "less loaded server contains vm", lessLoadedServer.contains( vm ) );
    }

    private Server a( ServerBuilder builder ) {
        return builder.build();
    }

    private Vm a( VmBuilder builder ) {
        return builder.build();
    }

    private Server[] aListOfServersWith( Server... servers ) {
        return servers;
    }

    private Vm[] aListOfVM() {
        return new Vm[]{};
    }

    private Vm[] aListOfVMWith( Vm... vms ) {
        return vms;
    }

}
