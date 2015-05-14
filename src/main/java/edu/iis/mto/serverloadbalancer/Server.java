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
    private double percentageLoad;
    private final List<Vm> vms = new ArrayList<Vm>();

    Server( int capacity ) {
        this.capacity = capacity;
    }

    public double getCurrentPercetnageLoad() {
        return percentageLoad;
    }

    public boolean contains( Vm theVm ) {
        return vms.contains( theVm );
    }

    public void addVm( Vm vm ) {
        vms.add( vm );
        recalculateLoad( vm );
    }

    private void recalculateLoad( Vm vm ) {
        percentageLoad += calculateAdditionalLoad( vm );
    }

    public int vmCount() {
        return vms.size();
    }

    boolean canTake( Vm vm ) {
        return ( percentageLoad + calculateAdditionalLoad( vm ) <= MAXIMUM_LOAD );
    }

    private double calculateAdditionalLoad( Vm vm ) {
        return (double) vm.getSize() / (double) capacity * MAXIMUM_LOAD;
    }

}
