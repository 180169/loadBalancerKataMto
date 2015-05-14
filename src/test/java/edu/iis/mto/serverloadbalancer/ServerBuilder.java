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
class ServerBuilder implements builder<Server> {

    private int capacity;

    public ServerBuilder() {
    }

    public ServerBuilder withCapacity( int capacity ) {
        this.capacity = capacity;
        return this;
    }

    public Server build() {
        return new Server( capacity );
    }

    public static ServerBuilder server() {
        return new ServerBuilder();
    }

}
