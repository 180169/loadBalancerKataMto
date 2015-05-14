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
        if ( vmList.length > 0 ) {
            serverList[0].percentageLoad = (double) vmList[0].size / (double) serverList[0].capacity * 100.0d;
        }
    }

}
