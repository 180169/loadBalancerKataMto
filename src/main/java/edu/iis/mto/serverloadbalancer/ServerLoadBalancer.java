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
public class ServerLoadBalancer {

    public static void balance( Server[] servers, Vm[] vms ) {
        for ( Vm vm : vms ) {
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
            theLeastLoadedServer.add( vm );
        }
    }

}
