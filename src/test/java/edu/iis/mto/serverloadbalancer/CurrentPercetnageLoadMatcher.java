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
class CurrentPercetnageLoadMatcher extends TypeSafeMatcher<Server> {

    private double expectedLoad;

    public CurrentPercetnageLoadMatcher( double expectedLoad ) {
        this.expectedLoad = expectedLoad;
    }

    @Override
    protected boolean matchesSafely( Server server ) {
        return Math.abs( server.getCurrentPercetnageLoad() - expectedLoad ) < 0.01d;
    }

    public void describeTo( Description description ) {
        description.appendText( "has current percentage load equal " ).appendValue( expectedLoad );
    }

}
