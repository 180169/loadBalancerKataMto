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
class Vm {

    private final int size;

    Vm( int size ) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

}
