package edu.iis.mto.serverloadbalancer;

import edu.iis.mto.serverloadBalancer.Server;

/**
 *
 * @author Godzio
 */
class ServerBuilder {

    private int capacity;
    private int initialLoad = 0;

    public ServerBuilder withCapacity( int capacity ) {
        this.capacity = capacity;
        return this;

    }

    public Server build() {
        Server server = new Server( capacity );
        if ( initialLoad != 0 ) {
            int expectedLoad = (int) ( initialLoad / 100.0 * (double) capacity );
            server.addVm( new VmBuilder().withSize( expectedLoad ).build() );
        }
        return server;
    }

    public static ServerBuilder server() {
        return new ServerBuilder();
    }

    ServerBuilder withInitialLoad( int initialLoad ) {
        this.initialLoad = initialLoad;
        return this;

    }

}
