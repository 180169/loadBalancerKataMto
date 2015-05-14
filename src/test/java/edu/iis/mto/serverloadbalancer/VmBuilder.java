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
class VmBuilder implements builder<Vm> {

    private int size;

    public VmBuilder() {
    }

    public VmBuilder ofSize( int size ) {
        this.size = size;
        return this;
    }

    @Override
    public Vm build() {
        return new Vm( size );
    }

    public static VmBuilder vm() {
        return new VmBuilder();
    }

}
