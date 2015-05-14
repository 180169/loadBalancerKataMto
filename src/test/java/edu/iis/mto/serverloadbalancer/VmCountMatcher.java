/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iis.mto.serverloadbalancer;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 *
 * @author Godzio
 */
class VmCountMatcher extends TypeSafeMatcher<Server> {

    private final int expectedVmCount;

    public VmCountMatcher( int expectedVmCount ) {
        this.expectedVmCount = expectedVmCount;
    }

    @Override
    protected boolean matchesSafely( Server server ) {
        return server.vmCount() == expectedVmCount;
    }

    public void describeTo( Description description ) {
        description.appendText( "should have vm count of " ).appendValue( expectedVmCount );
    }

    @Override
    protected void describeMismatchSafely( Server item, Description mismatchDescription ) {
        mismatchDescription.appendText( "but had server with vm count of " ).appendValue( item.vmCount() );
    }

    public static Matcher<Server> hasVmCountOf( int expectedVmCount ) {
        return new VmCountMatcher( expectedVmCount );
    }

}
