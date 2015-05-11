/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iis.mto.serverloadbalancer;

import edu.iis.mto.serverloadBalancer.Server;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 *
 * @author Godzio
 */
public class CurrentPercentageLoadMatcher extends TypeSafeMatcher<Server> {

    private final double expectedLoad;
    private static final double EPSILON = 0.01d;

    public CurrentPercentageLoadMatcher( double expectedLoad ) {
        this.expectedLoad = expectedLoad;
    }

    @Override
    protected boolean matchesSafely( Server server ) {
        return compareDoubles( server );
    }

    public void describeTo( Description description ) {
        description.appendText( "a server with expected load of" ).appendValue( expectedLoad );
    }

    @Override
    protected void describeMismatchSafely( Server server, Description description ) {
        description.appendText( "a server with load percentage of " )
                .appendValue( server.hasCurrentPercentageLoad() );

    }

    private boolean compareDoubles( Server server ) {
        return Math.abs( server.hasCurrentPercentageLoad() - expectedLoad ) < EPSILON;
    }

    public static Matcher<Server> hasPercentageLoad( double expectedLoad ) {
        return new CurrentPercentageLoadMatcher( expectedLoad );
    }

}
