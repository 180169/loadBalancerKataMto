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

    public static final double MAXIMUM_LOAD = 100.0d;
    private final int capacity;
    double percentageLoad;
    private final List<Vm> vms = new ArrayList<Vm>();

    Server( int capacity ) {
        this.capacity = capacity;
    }

    double getCurrentPercetnageLoad() {
        return percentageLoad;
    }

    boolean contains( Vm theVm ) {
        return vms.contains( theVm );
    }

    void addVm( Vm vm ) {
        vms.add( vm );
        recalculateLoad( vm );
    }

    private void recalculateLoad( Vm vm ) {
        percentageLoad = (double) vm.size / (double) capacity * MAXIMUM_LOAD;
    }

    public int vmCount() {
        return vms.size();
    }

    boolean canTake( Vm vm ) {
        return ( percentageLoad + (double) vm.size / (double) capacity * MAXIMUM_LOAD <= MAXIMUM_LOAD );
    }

}
