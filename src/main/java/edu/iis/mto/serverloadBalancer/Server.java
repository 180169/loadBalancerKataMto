/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iis.mto.serverloadBalancer;

/**
 *
 * @author Godzio
 */
public class Server {

    double currentLoad;
    int capacity;

    public Server( int capacity ) {
        this.capacity = capacity;
    }

    public double hasCurrentPercentageLoad() {
        return currentLoad;
    }

}
