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
class ServerBuilder implements Builder<Server> {

    private int capacity;
    private double initialPercentageLoad;

    public ServerBuilder() {
    }

    public ServerBuilder withCapacity( int capacity ) {
        this.capacity = capacity;
        return this;

    }

    public Server build() {
        Server server = new Server( capacity );
        if ( initialPercentageLoad != 0 ) {
            int vmSize = (int) ( initialPercentageLoad * (double) capacity / 100.0d );
            server.addVm( new Vm( vmSize ) );
        }
        return server;
    }

    public static ServerBuilder server() {
        return new ServerBuilder();
    }

    ServerBuilder withInitialPercentageLoad( double initialLoad ) {
        this.initialPercentageLoad = initialLoad;
        return this;
    }

}
