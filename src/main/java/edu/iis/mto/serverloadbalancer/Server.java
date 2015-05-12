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
public class Server {

    private final int capacity;

    private double percentageLoad;

    Server( int capacity ) {
        this.capacity = capacity;
    }

    public double currentPercentageLoad() {
        return percentageLoad;
    }

    boolean contains( Vm theVm ) {
        return true;
    }

    public void add( Vm vm ) {
        percentageLoad = (double) vm.getSize() / (double) capacity * 100.0d;
    }
}
