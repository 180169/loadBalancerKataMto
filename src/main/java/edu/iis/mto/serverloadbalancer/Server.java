package edu.iis.mto.serverloadbalancer;

public class Server {

    public double currentLoadPecentage;
    public final int capacity;

    public Server( int capacity ) {
        this.capacity = capacity;
    }

    public boolean contains( Vm vm ) {
        return true;
    }

}
