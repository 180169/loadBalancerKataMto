/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

/**
 *
 * @author Godzio
 */
class hasCurrentPercetageLoad extends TypeSafeMatcher<Server> {

    private final double expectedLoad;

    public hasCurrentPercetageLoad( double expectedLoad ) {
        this.expectedLoad = expectedLoad;
    }

    @Override
    protected boolean matchesSafely( Server theServer ) {
        return Math.abs( theServer.getCurrentPercentageLoad() - expectedLoad ) <= 0.01d;
    }

    public void describeTo( Description d ) {
        d.appendText( "has current percentage load equalt to" ).appendValue( expectedLoad );
    }

}
