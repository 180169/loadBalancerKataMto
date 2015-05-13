/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iis.mto.serverloadbalancer;

/**
 *
 * @author Godzio
 */
class ServerLoadBalancer {

    static void balance( Server[] serverList, Vm[] vmList ) {
        for ( Vm vm : vmList ) {
            Server theLeastLoadedServer = null;
            for ( Server server : serverList ) {
                if ( theLeastLoadedServer == null ) {
                    theLeastLoadedServer = server;
                } else if ( theLeastLoadedServer.currentPercentageLoad > server.currentPercentageLoad ) {
                    theLeastLoadedServer = server;
                }

            }

            theLeastLoadedServer.addVm( vm );

        }
    }

}
