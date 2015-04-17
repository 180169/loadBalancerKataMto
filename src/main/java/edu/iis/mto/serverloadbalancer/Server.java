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

    private double MAXIMUM_LOAD = 100.0d;
    public double currentLoadPercentage;
    public final int capacity;

    Server( int capacity ) {
        this.capacity = capacity;
    }

    public boolean contains( Vm vm ) {
        return true;

    }

    void addVm( Vm vm ) {
        currentLoadPercentage = (double) vm.size / (double) this.capacity * MAXIMUM_LOAD;
    }

}
