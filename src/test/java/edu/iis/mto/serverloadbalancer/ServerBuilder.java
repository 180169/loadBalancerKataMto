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
class ServerBuilder {

    private int capacity;
    private double initialLoad = 0;

    public ServerBuilder withCapacity( int capacity ) {
        this.capacity = capacity;
        return this;
    }

    public Server build() {
        Server server = new Server( capacity );
        if ( initialLoad != 0 ) {
            int vmSize = (int) ( (double) capacity * initialLoad / 100.0d );
            server.add( new VmBuilder().ofSize( vmSize ).build() );
        }
        return server;
    }

    public static ServerBuilder server() {
        return new ServerBuilder();
    }

    public ServerBuilder withInitialLoad( double initialLoad ) {
        this.initialLoad = initialLoad;
        return this;
    }

}
