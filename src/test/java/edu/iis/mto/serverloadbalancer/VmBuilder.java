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
class VmBuilder implements Builder<Vm> {

    private int size;

    public VmBuilder() {
    }

    static VmBuilder vm() {
        return new VmBuilder();
    }

    public VmBuilder withSize( int size ) {
        this.size = size;
        return this;
    }

    public Vm build() {
        return new Vm( size );
    }

}
