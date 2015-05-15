package edu.iis.mto.serverloadbalancer;

public class Server {

    private static final double MAXIMUM_LOAD = 100.0d;
    public double currentLoadPecentage;
    public final int capacity;

    public Server( int capacity ) {
        this.capacity = capacity;
    }

    public boolean contains( Vm vm ) {
        return true;
    }

    void addVm( Vm vm ) {
        currentLoadPecentage = (double) vm.size / capacity * MAXIMUM_LOAD;
    }

}
