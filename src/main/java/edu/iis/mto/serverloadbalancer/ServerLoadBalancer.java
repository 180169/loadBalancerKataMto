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
        if ( vms.length > 0 ) {
            servers[0].currentLoadPercentage = ( (double) vms[0].size / (double) servers[0].capacity ) * 100.0d;
        }
    }
}
