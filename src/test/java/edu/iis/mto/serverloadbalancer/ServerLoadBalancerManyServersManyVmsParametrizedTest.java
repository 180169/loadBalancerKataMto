package edu.iis.mto.serverloadbalancer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static edu.iis.mto.serverloadbalancer.CurrentLoadPercentageMatcher.hasLoadPercentageOf;
import static edu.iis.mto.serverloadbalancer.ServerBuilder.server;
import static edu.iis.mto.serverloadbalancer.VmBuilder.vm;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith( Parameterized.class )
public class ServerLoadBalancerManyServersManyVmsParametrizedTest extends ServerLoadBalancerBaseTest {

    //those tables contains servers capacities and machines sizes. additionaly tables lengths is a number of servers and vm
    private int[] serversCapacities,
            vmsSizes;
    //this table contains expected load for each server 
    private double[] serversExpectedPercentageLoads;
    //vmsLocationsOnServers - for example:
    //server One contains vms with numbers 1 and 2, server Two containt vm number 0.
    //count from 0
    private int[][] vmsLocationsOnServers;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        /*
         { 
         serversCapacities, 
         vmsSizes, 
         serversExpectedPercentageLoads, 
         vmsLocationsOnServers 
         }
         */
        return Arrays.asList( new Object[][]{
            {
                createIntTable( 2, 3 ),
                createIntTable( 3, 1, 1 ),
                createDoubleTable( 100.0, 100.0 ),
                new int[][]{ createIntTable( 1, 2 ), createIntTable( 0 ) } },
            { createIntTable( 5, 3, 7 ),
                createIntTable( 2, 2, 2, 2, 2, 2 ),
                createDoubleTable( 80.0, 66.66, 85.71 ),
                new int[][]{ createIntTable( 0, 4 ), createIntTable( 1 ), createIntTable( 3, 2, 5 ) } },
            { createIntTable( 4, 5, 6, 7, 8 ),
                createIntTable( 8, 7, 6, 5, 4 ),
                createDoubleTable( 100.0, 100.0, 100.0, 100.0, 100.0 ),
                new int[][]{ createIntTable( 4 ), createIntTable( 3 ), createIntTable( 2 ), createIntTable( 1 ), createIntTable( 0 ) } }
        } );
    }

    public ServerLoadBalancerManyServersManyVmsParametrizedTest( int[] serversCapacities, int[] vmsSizes, double[] serversExpectedPercentageLoads, int[][] vmsLocationsOnServers ) {
        this.serversCapacities = serversCapacities;
        this.vmsSizes = vmsSizes;
        this.serversExpectedPercentageLoads = serversExpectedPercentageLoads;
        this.vmsLocationsOnServers = vmsLocationsOnServers;
    }

    @Test
    public void balance_serversAndVms() {
        List<Server> servers = new ArrayList<Server>();
        List<Vm> vms = new ArrayList<Vm>();
        for ( int i = 0; i < serversCapacities.length; i++ ) {
            servers.add( a( server().withCapacity( serversCapacities[i] ) ) );
        }
        for ( int i = 0; i < vmsSizes.length; i++ ) {
            vms.add( a( vm().ofSize( vmsSizes[i] ) ) );
        }
        balance( servers.toArray( new Server[servers.size()] ), vms.toArray( new Vm[vms.size()] ) );
        for ( int i = 0; i < servers.size(); i++ ) {
            assertThat( servers.get( i ), hasLoadPercentageOf( serversExpectedPercentageLoads[i] ) );
            for ( int j = 0; j < vmsLocationsOnServers[i].length; j++ ) {
                System.out.println( "server " + i + " contains vm number " + vmsLocationsOnServers[i][j] + " " + servers.get( i ).contains( vms.get( vmsLocationsOnServers[i][j] ) ) );
                assertThat( "the server should contain vm number" + j, servers.get( i ).contains( vms.get( vmsLocationsOnServers[i][j] ) ) );
            }
        }
    }

    public static int[] createIntTable( int... ints ) {
        return ints;
    }

    public static double[] createDoubleTable( double... doubles ) {
        return doubles;
    }
}
