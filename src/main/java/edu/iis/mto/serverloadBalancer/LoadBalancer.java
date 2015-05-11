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
            Server theLeastLoadedServer = findTheLeastLoadedServer( servers );
            if ( chechIfVmFitOnServer( theLeastLoadedServer, vm ) ) {
                theLeastLoadedServer.addVm( vm );
            }
        }
    }

    private static boolean chechIfVmFitOnServer( Server theLeastLoadedServer, Vm vm ) {
        return theLeastLoadedServer.calculateLoad( vm ) <= Server.MAXIMUM_LOAD;
    }

    private static Server findTheLeastLoadedServer( Server[] servers ) {
        Server theLeastLoadedServer = null;
        for ( Server server : servers ) {
            if ( theLeastLoadedServer == null ) {
                theLeastLoadedServer = server;
            } else {
                if ( server.getCurrentLoad() < theLeastLoadedServer.getCurrentLoad() ) {
                    theLeastLoadedServer = server;
                }
            }
        }
        return theLeastLoadedServer;
    }

}
