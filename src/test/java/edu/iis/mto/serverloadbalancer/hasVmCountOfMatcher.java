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
class hasVmCountOfMatcher extends TypeSafeMatcher<Server> {

    private final int expectedCount;

    public hasVmCountOfMatcher( int expectedCount ) {
        this.expectedCount = expectedCount;
    }

    @Override
    protected boolean matchesSafely( Server server ) {
        return server.vmCount() == expectedCount;
    }

    public void describeTo( Description d ) {
        d.appendText( "has vm count equal " ).appendValue( expectedCount );
    }

}
