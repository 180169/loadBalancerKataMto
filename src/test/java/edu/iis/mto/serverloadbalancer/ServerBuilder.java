package edu.iis.mto.serverloadbalancer;

import edu.iis.mto.serverloadBalancer.Server;

/**
 *
 * @author Godzio
 */
class ServerBuilder {

    private int capacity;

    public ServerBuilder withCapacity( int capacity ) {
        this.capacity = capacity;
        return this;

    }

    public Server build() {
        return new Server( capacity );
    }

}
