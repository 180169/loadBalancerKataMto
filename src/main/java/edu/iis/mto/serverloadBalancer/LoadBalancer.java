/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iis.mto.serverloadBalancer;

/**
 *
 * @author Godzio
 */
public class LoadBalancer {

    public static void balance( Server[] servers, Vm[] vms ) {

        for ( Vm vm : vms ) {
            findTheLeastLoadedServer( servers ).addVm( vm );
        }
    }

    private static Server findTheLeastLoadedServer( Server[] servers ) {
        Server theLeastLoadedServer = null;
        for ( Server server : servers ) {
            if ( theLeastLoadedServer == null ) {
                theLeastLoadedServer = server;
            } else {
                if ( server.currentLoad < theLeastLoadedServer.currentLoad ) {
                    theLeastLoadedServer = server;
                }
            }
        }
        return theLeastLoadedServer;
    }

}
