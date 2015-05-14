/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iis.mto.serverloadbalancer;

import static edu.iis.mto.serverloadbalancer.VmBuilder.vm;

/**
 *
 * @author Godzio
 */
class ServerBuilder implements builder<Server> {

    private int capacity;
    private double initialLoad;

    public ServerBuilder() {
    }

    public ServerBuilder withCapacity( int capacity ) {
        this.capacity = capacity;
        return this;
    }

    public Server build() {
        Server server = new Server( capacity );
        if ( initialLoad != 0 ) {
            int stubVmSize = (int) ( initialLoad * capacity / 100.0d );
            server.addVm( vm().ofSize( stubVmSize ).build() );
        }

        return server;
    }

    public static ServerBuilder server() {
        return new ServerBuilder();
    }

    ServerBuilder withInitialLoad( double initialLoad ) {
        this.initialLoad = initialLoad;
        return this;
    }

}
