/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iis.mto.serverloadBalancer;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Godzio
 */
public class Server {

    public static final double MAXIMUM_LOAD = 100.0;
    private double currentLoad;
    private final int capacity;
    private final List< Vm> vms = new ArrayList<Vm>();

    public Server( int capacity ) {
        this.capacity = capacity;
    }

    public double hasCurrentPercentageLoad() {
        return getCurrentLoad();
    }

    public void addVm( Vm vm ) {
        setCurrentLoad( calculateLoad( vm ) );
        vms.add( vm );

    }

    public double calculateLoad( Vm vm ) {
        return getCurrentLoad() + ( (double) vm.getSize() / (double) capacity ) * MAXIMUM_LOAD;
    }

    public int numberOfVms() {
        return vms.size();
    }

    public boolean contains( Vm vm ) {
        return vms.contains( vm );
    }

    public double getCurrentLoad() {
        return currentLoad;
    }

    public void setCurrentLoad( double currentLoad ) {
        this.currentLoad = currentLoad;
    }

}
