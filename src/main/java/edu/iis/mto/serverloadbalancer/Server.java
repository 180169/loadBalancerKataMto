/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iis.mto.serverloadbalancer;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Godzio
 */
class Server {

    private static final double MAXIMUM_LOAD = 100.0;

    double currentPercentageLoad = 0;
    private final int capacity;
    private final List<Vm> vms = new ArrayList<Vm>();

    Server( int capacity ) {
        this.capacity = capacity;
    }

    public double getCurrentPercentageLoad() {
        return currentPercentageLoad;
    }

    boolean contains( Vm theVm ) {
        return true;
    }

    public void addVm( Vm vm ) {
        vms.add( vm );
        recalculateLoad( vm );
    }

    public void recalculateLoad( Vm vm ) {
        currentPercentageLoad += (double) vm.getSize() / (double) capacity * MAXIMUM_LOAD;
    }

    public int vmCount() {
        return vms.size();
    }

}
