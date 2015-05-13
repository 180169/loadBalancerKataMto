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
class ServerLoadBalancer {

    static void balance( Server[] serverList, Vm[] vmList ) {
        for ( Vm vm : vmList ) {
            Server theLeastLoadedServer = findTheLeastLoadedServer( serverList );

            if ( theLeastLoadedServer.currentPercentageLoad + (double) vm.getSize() / (double) theLeastLoadedServer.capacity * MAXIMUM_LOAD <= 100.0d ) {
                theLeastLoadedServer.addVm( vm );
            }

        }
    }

    public static Server findTheLeastLoadedServer( Server[] serverList ) {
        Server theLeastLoadedServer = null;
        for ( Server server : serverList ) {
            if ( theLeastLoadedServer == null ) {
                theLeastLoadedServer = server;
            } else if ( theLeastLoadedServer.currentPercentageLoad > server.currentPercentageLoad ) {
                theLeastLoadedServer = server;
            }

        }
        return theLeastLoadedServer;
    }

}
