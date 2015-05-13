package edu.iis.mto.serverloadbalancer;

import org.junit.Test;

import static edu.iis.mto.serverloadbalancer.ServerBuilder.server;
import static edu.iis.mto.serverloadbalancer.ServerLoadBalancer.balance;
import static edu.iis.mto.serverloadbalancer.VmBuilder.vm;
import static edu.iis.mto.serverloadbalancer.hasCurrentPercetageLoad.hasCurrentPercentageLoad;
import static edu.iis.mto.serverloadbalancer.hasVmCountOfMatcher.hasVmCountOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class ServerLoadBalancerTest {

    @Test
    public void itCompiles() {
        assertThat( true, equalTo( true ) );
    }

    @Test
    public void emptyServerWithExpectedLoadEqual0() {
        Server theServer = a( server().withCapacity( 5 ) );

        balance( serverList( theServer ), vmList() );

        assertThat( theServer, hasCurrentPercentageLoad( 0.0d ) );
    }

    @Test
    public void serverFilledByOneMachine() {
        Server theServer = a( server().withCapacity( 10 ) );
        Vm theVm = a( vm().ofSize( 10 ) );

        balance( serverList( theServer ), vmList( theVm ) );

        assertThat( theServer, hasCurrentPercentageLoad( 100.0d ) );
        assertThat( theServer.contains( theVm ), is( true ) );
    }

    @Test
    public void serverHalfFilledByVm() {
        Server theServer = a( server().withCapacity( 50 ) );
        Vm theVm = a( vm().ofSize( 25 ) );

        balance( serverList( theServer ), vmList( theVm ) );

        assertThat( theServer, hasCurrentPercentageLoad( 50.0d ) );
        assertThat( theServer.contains( theVm ), is( true ) );

    }

    @Test
    public void serverWithFewVm() {
        Server theServer = a( server().withCapacity( 100 ) );
        Vm firstVm = a( vm().ofSize( 10 ) );
        Vm secondVm = a( vm().ofSize( 25 ) );
        Vm thirdVm = a( vm().ofSize( 35 ) );

        balance( serverList( theServer ), vmList( firstVm, secondVm, thirdVm ) );

        assertThat( theServer, hasCurrentPercentageLoad( 70.0d ) );
        assertThat( theServer, hasVmCountOf( 3 ) );
        assertThat( theServer.contains( firstVm ), is( true ) );
        assertThat( theServer.contains( secondVm ), is( true ) );
        assertThat( theServer.contains( thirdVm ), is( true ) );
    }

    @Test
    public void twoServerOneMachineLocatedOnLessLoadedServer() {
        Server firstServer = a( server().withCapacity( 40 ).withInitialPercentageLoad( 50.0d ) );
        Server secondServer = a( server().withCapacity( 25 ) );
        Vm theVm = a( vm().ofSize( 5 ) );

        balance( serverList( firstServer, secondServer ), vmList( theVm ) );

        assertThat( firstServer, hasCurrentPercentageLoad( 50.0d ) );
        assertThat( secondServer, hasCurrentPercentageLoad( 20.0d ) );
        assertThat( secondServer, hasVmCountOf( 1 ) );
        assertThat( firstServer.contains( theVm ), is( false ) );
        assertThat( secondServer.contains( theVm ), is( true ) );

    }

    @Test
    public void serverOverloadedShouldNotContainVm() {
        Server theServer = a( server().withCapacity( 75 ).withInitialPercentageLoad( 100 ) );
        Vm theVm = a( vm().ofSize( 25 ) );

        balance( serverList( theServer ), vmList( theVm ) );

        assertThat( theServer, hasCurrentPercentageLoad( 100.0d ) );
        assertThat( theServer.contains( theVm ), is( false ) );

    }

    @Test
    public void serversAndMachines() {
        Server firstServer = a( server().withCapacity( 50 ).withInitialPercentageLoad( 50.0d ) );
        Server secondServer = a( server().withCapacity( 20 ) );
        Vm firstVm = a( vm().ofSize( 15 ) );
        Vm secondVm = a( vm().ofSize( 25 ) );
        Vm thirdVm = a( vm().ofSize( 35 ) );

        balance( serverList( firstServer, secondServer ), vmList( firstVm, secondVm, thirdVm ) );
        
        assertThat( firstServer, hasCurrentPercentageLoad( 100.0d ) );
        assertThat( secondServer, hasCurrentPercentageLoad( 75.0d ) );
        
        assertThat( firstServer.contains( firstVm ), is( false ) );
        assertThat( secondServer.contains( firstVm ), is( true ) );
        
        assertThat( firstServer.contains( secondVm ), is( true ) );
        assertThat( secondServer.contains( secondVm ), is( false ) );
        
        assertThat( firstServer.contains( thirdVm ), is( false ) );
        assertThat( secondServer.contains( thirdVm ), is( false ) );
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

    private Vm[] vmList( Vm... vms ) {
        return vms;
    }

}
