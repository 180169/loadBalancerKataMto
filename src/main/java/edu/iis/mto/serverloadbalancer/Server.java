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

    private static final double MAXIMUM_LOAD = 100.0d;
    private final int capacity;
    double percentageLoad;

    Server( int capacity ) {
        this.capacity = capacity;
    }

    double getCurrentPercetnageLoad() {
        return percentageLoad;
    }

    boolean contains( Vm theVm ) {
        return true;
    }

    void addVm( Vm vm ) {
        percentageLoad = (double) vm.size / (double) capacity * MAXIMUM_LOAD;
    }

}
