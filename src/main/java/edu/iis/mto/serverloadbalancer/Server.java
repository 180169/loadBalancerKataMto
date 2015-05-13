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

    public static final double MAXIMUM_LOAD = 100.0;

    private double currentPercentageLoad = 0;
    private final int capacity;
    private final List<Vm> vms = new ArrayList<Vm>();

    Server( int capacity ) {
        this.capacity = capacity;
    }

    public double getCurrentPercentageLoad() {
        return currentPercentageLoad;
    }

    boolean contains( Vm theVm ) {
        return vms.contains( theVm );
    }

    public void addVm( Vm vm ) {
        vms.add( vm );
        recalculateLoad( vm );
    }

    public void recalculateLoad( Vm vm ) {
        currentPercentageLoad += calculateVmWeight( vm );
    }

    private double calculateVmWeight( Vm vm ) {
        return (double) vm.getSize() / (double) capacity * MAXIMUM_LOAD;
    }

    public int vmCount() {
        return vms.size();
    }

    public boolean canFit( Vm vm ) {
        return currentPercentageLoad + calculateVmWeight( vm ) <= MAXIMUM_LOAD;
    }

}
