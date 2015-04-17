/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iis.mto.serverloadbalancer;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Godzio
 */
public class ServerLoadBalancer {

    public static void balance( Server[] servers, Vm[] vms ) {
        for ( Vm vm : vms ) {
            List<Server> capableServers = findServersWithEnoughSpace( servers, vm );
            Server theLeastLoadedServer = findTheLeastLowdedServer( capableServers );
            addToCapableTheLeastLoadedServer( theLeastLoadedServer, vm );
        }

    }

    private static void addToCapableTheLeastLoadedServer( Server theLeastLoadedServer, Vm vm ) {
        if ( theLeastLoadedServer != null && theLeastLoadedServer.canContain( vm ) ) {
            theLeastLoadedServer.addVm( vm );
        }
    }

    public static List<Server> findServersWithEnoughSpace( Server[] servers, Vm vm ) {
        List<Server> capableServers = new ArrayList<Server>();
        for ( Server server : servers ) {
            if ( server.canContain( vm ) ) {
                capableServers.add( server );
            }
        }
        return capableServers;
    }

    public static Server findTheLeastLowdedServer( List<Server> servers ) {
        Server theLeastLoadedServer = null;
        for ( Server server : servers ) {
            if ( theLeastLoadedServer == null || theLeastLoadedServer.getCurrentLoadPercentage() > server.getCurrentLoadPercentage() ) {
                theLeastLoadedServer = server;
            }
        }
        return theLeastLoadedServer;
    }
}
