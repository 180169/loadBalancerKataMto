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

    private final int capacity;
    public static final double MAXIMUM_LOAD = 100.0d;
    private double percentageLoad;
    private final List<Vm> vms = new ArrayList<Vm>();

    Server( int capacity ) {
        this.capacity = capacity;
    }

    public double currentPercentageLoad() {
        return percentageLoad;
    }

    boolean contains( Vm vm ) {
        return vms.contains( vm );
    }

    public void add( Vm vm ) {
        vms.add( vm );
        percentageLoad += calculateLoad( vm );
    }

    private double calculateLoad( Vm vm ) {
        return (double) vm.getSize() / (double) capacity * MAXIMUM_LOAD;
    }

    public int countOfVms() {
        return vms.size();
    }
}
