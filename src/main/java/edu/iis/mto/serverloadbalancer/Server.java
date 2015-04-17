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
public class Server {

    public final double MAXIMUM_LOAD = 100.0d;
    private double currentLoadPercentage = 0;
    private final int capacity;
    private List<Vm> vms = new ArrayList<Vm>();

    Server( int capacity ) {
        this.capacity = capacity;
    }

    public boolean contains( Vm vm ) {
        return vms.contains( vm );

    }

    void addVm( Vm vm ) {
        vms.add( vm );
        this.currentLoadPercentage = calculatePercentageLoad( vm );

    }

    public int vmsCount() {
        return vms.size();

    }

    public int getCapacity() {
        return capacity;
    }

    public double getCurrentLoadPercentage() {
        return currentLoadPercentage;
    }

    boolean canContain( Vm vm ) {
        if ( calculatePercentageLoad( vm ) <= MAXIMUM_LOAD ) {
            return true;
        } else {
            return false;
        }
    }

    private double calculatePercentageLoad( Vm vm ) {
        return this.currentLoadPercentage + (double) vm.size / (double) this.capacity * MAXIMUM_LOAD;
    }

}
