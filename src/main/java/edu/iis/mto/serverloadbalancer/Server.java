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
class Server {

    double currentPercentageLoad = 0;
    public final int capacity;

    Server( int capacity ) {
        this.capacity = capacity;
    }

    public double getCurrentPercentageLoad() {
        return currentPercentageLoad;
    }

    boolean contains( Vm theVm ) {
        return true;
    }
    
    

}
