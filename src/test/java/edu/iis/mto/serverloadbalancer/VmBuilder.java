/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iis.mto.serverloadbalancer;

import edu.iis.mto.serverloadBalancer.Vm;

/**
 *
 * @author Godzio
 */
class VmBuilder {

    private int size;

    VmBuilder withSize( int size ) {
        this.size = size;
        return this;
    }

    Vm build() {
        return new Vm( size );
    }

}
