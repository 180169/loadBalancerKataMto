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
public class CurrentPercentageLoadMatcher extends TypeSafeMatcher<Server> {

    private static final double EPSILON = 0.01d;
    private double expectedLoadPercentage;

    public CurrentPercentageLoadMatcher( double expectedLoadPercentage ) {
        this.expectedLoadPercentage = expectedLoadPercentage;
    }

    public void describeTo( Description description ) {
        description.appendText( "a server with load percentage of " )
                .appendValue( expectedLoadPercentage );
    }

    @Override
    protected void describeMismatchSafely( Server item, Description description ) {
        description.appendText( "a server with load percentage of " )
                .appendValue( item.currentLoadPercentage );
    }

    @Override
    protected boolean matchesSafely( Server server ) {
        return equalsDouble( server.currentLoadPercentage, expectedLoadPercentage );
    }

    private boolean equalsDouble( double d1, double d2 ) {
        return d1 == d2 || Math.abs( d1 - d2 ) < EPSILON;
    }

    public static CurrentPercentageLoadMatcher hasLoadPercentageOf(
            double expectedLoadPercentage ) {
        return new CurrentPercentageLoadMatcher( expectedLoadPercentage );
    }

    public static Matcher<Server> hasPercentageLoadOf( double expectedLoad ) {
        return new CurrentPercentageLoadMatcher( expectedLoad );
    }
}
