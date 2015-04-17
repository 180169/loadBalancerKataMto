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
class ServerVmsCountMatcher extends TypeSafeMatcher<Server> {

    private final int expectedCount;

    public ServerVmsCountMatcher( int expectedCount ) {
        this.expectedCount = expectedCount;
    }

    @Override
    protected boolean matchesSafely( Server server ) {
        return server.vmsCount() == expectedCount;

    }

    public void describeTo( Description description ) {
        description.appendText( "a server with count equal " )
                .appendValue( expectedCount );
    }

    @Override
    protected void describeMismatchSafely( Server item, Description description ) {
        description.appendText( "a server with count equal " )
                .appendValue( item.vmsCount() );
    }

    public static Matcher<? super Server> hasVmCountEqualTo( int expectedCount ) {
        return new ServerVmsCountMatcher( expectedCount );
    }

}
