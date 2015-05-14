package edu.iis.mto.serverloadbalancer;

import org.junit.Test;

import static edu.iis.mto.serverloadbalancer.CurrentPercetnageLoadMatcher.hasCurrentPercentageLoad;
import static edu.iis.mto.serverloadbalancer.ServerBuilder.server;
import static edu.iis.mto.serverloadbalancer.ServerLoadBalancer.balance;
import static edu.iis.mto.serverloadbalancer.VmBuilder.vm;
import static edu.iis.mto.serverloadbalancer.VmCountMatcher.hasVmCountOf;
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

    @Test
    public void serverFilledByOneMachine() {
        Server theServer = a( server().withCapacity( 100 ) );
        Vm theVm = a( vm().ofSize( 100 ) );

        balance( serverList( theServer ), vmList( theVm ) );

        assertThat( theServer, hasCurrentPercentageLoad( 100.0d ) );
        assertThat( "server should contains the vm", theServer.contains( theVm ) );

    }

    @Test
    public void serverHalfFilledByVm() {
        Server theServer = a( server().withCapacity( 50 ) );
        Vm theVm = a( vm().ofSize( 25 ) );

        balance( serverList( theServer ), vmList( theVm ) );

        assertThat( theServer, hasCurrentPercentageLoad( 50.0d ) );
        assertThat( "server should contains the vm", theServer.contains( theVm ) );
    }

    @Test
    public void serverWithFewVms() {
        Server theServer = a( server().withCapacity( 6 ) );
        Vm firstVm = a( vm().ofSize( 1 ) );
        Vm secondVm = a( vm().ofSize( 2 ) );
        Vm thirdVm = a( vm().ofSize( 3 ) );

        balance( serverList( theServer ), vmList( firstVm, secondVm, thirdVm ) );

        assertThat( theServer, hasVmCountOf( 3 ) );
        assertThat( "server should contains the vm", theServer.contains( firstVm ) );
        assertThat( "server should contains the vm", theServer.contains( secondVm ) );
        assertThat( "server should contains the vm", theServer.contains( thirdVm ) );

    }

    @Test
    public void twoServersAndOneMachineLocatedOnLessLoadedServer() {
        Server firstServer = a( server().withCapacity( 20 ).withInitialLoad( 50.0d ) );
        Server secondServer = a( server().withCapacity( 50 ) );
        Vm theVm = a( vm().ofSize( 5 ) );

        balance( serverList( firstServer, secondServer ), vmList( theVm ) );

        assertThat( firstServer, hasCurrentPercentageLoad( 50.0d ) );
        assertThat( secondServer, hasCurrentPercentageLoad( 10.0d ) );
        assertThat( "server should contains the vm", secondServer.contains( theVm ) );
    }

    @Test
    public void serverOverloadedToTakeAnotherMachine() {
        Server theServer = a( server().withCapacity( 2 ).withInitialLoad( 100.0d ) );
        Vm theVm = a( vm().ofSize( 2 ) );
        
        balance( serverList( theServer ), vmList( theVm ) );
        
        assertThat( theServer, hasCurrentPercentageLoad( 100.0d ) );
        
        assertThat( "server should not contains the vm", !theServer.contains( theVm ) );
    }

    private Vm[] vmList( Vm... vms ) {
        return vms;
    }

    private Server a( ServerBuilder builder ) {
        return builder.build();
    }

    private Vm a( VmBuilder builder ) {
        return builder.build();
    }

    private Server[] serverList( Server... servers ) {
        return servers;
    }

}
