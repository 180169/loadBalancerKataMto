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
    private int initialLoad = 0;

    public static ServerBuilder server() {
        return new ServerBuilder();
    }

    public ServerBuilder withCapacity( int capacity ) {
        this.capacity = capacity;
        return this;
    }

    public Server build() {
        Server server = new Server( this.capacity );
        setInitialLoad( server );
        return server;

    }

    public void setInitialLoad( Server server ) {
        if ( initialLoad != 0 ) {
            int expectedLoad = (int) ( initialLoad / server.MAXIMUM_LOAD * (double) server.getCapacity() );
            server.addVm( VmBuilder.vm().withSize( expectedLoad ).build() );
        }
    }

    ServerBuilder withInitialLoad( int initialLoad ) {
        this.initialLoad = initialLoad;
        return this;
    }

}
