/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iis.mto.serverloadbalancer;

import static edu.iis.mto.serverloadbalancer.Server.MAXIMUM_LOAD;

/**
 *
 * @author Godzio
 */
public class ServerLoadBalancer {

    public static void balance( Server[] servers, Vm[] vms ) {
        for ( Vm vm : vms ) {
            Server theLeastLoadedServer = findTheLeastLoadedServer( servers );
            if ( theLeastLoadedServer.currentPercentageLoad() + ( (double) vm.getSize() / (double) theLeastLoadedServer.getCapacity() * MAXIMUM_LOAD ) <= MAXIMUM_LOAD ) {
                theLeastLoadedServer.add( vm );
            }
        }
    }

    public static Server findTheLeastLoadedServer( Server[] servers ) {
        Server theLeastLoadedServer = null;
        for ( Server server : servers ) {
            if ( theLeastLoadedServer == null ) {
                theLeastLoadedServer = server;
            } else {
                if ( theLeastLoadedServer.currentPercentageLoad() > server.currentPercentageLoad() ) {
                    theLeastLoadedServer = server;
                }
            }

        }
        return theLeastLoadedServer;
    }

}
