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
    double currentLoad;
    int capacity;
    List< Vm> vms = new ArrayList<Vm>();

    public Server( int capacity ) {
        this.capacity = capacity;
    }

    public double hasCurrentPercentageLoad() {
        return currentLoad;
    }

    public void addVm( Vm vm ) {
        currentLoad = calculateLoad( vm );
        vms.add( vm );

    }

    public double calculateLoad( Vm vm ) {
        return currentLoad + ( (double) vm.getSize() / (double) capacity ) * MAXIMUM_LOAD;
    }

    public int numberOfVms() {
        return vms.size();
    }

    public boolean contains( Vm vm ) {
        return vms.contains( vm );
    }
}
