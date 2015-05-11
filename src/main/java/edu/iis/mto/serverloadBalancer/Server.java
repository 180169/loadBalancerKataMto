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
public class Server {

    private static final double MAXIMUM_LOAD = 100.0;
    double currentLoad;
    int capacity;

    public Server( int capacity ) {
        this.capacity = capacity;
    }

    public double hasCurrentPercentageLoad() {
        return currentLoad;
    }

    public void addVm( Vm vm ) {
        calculateCurrentLoad( vm );
    }

    private void calculateCurrentLoad( Vm vm ) {
        currentLoad = ( (double) vm.getSize() / (double) capacity ) * MAXIMUM_LOAD;
    }
}
