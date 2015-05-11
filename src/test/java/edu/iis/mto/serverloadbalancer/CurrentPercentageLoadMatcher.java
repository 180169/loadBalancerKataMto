/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iis.mto.serverloadbalancer;

import edu.iis.mto.serverloadBalancer.Server;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

/**
 *
 * @author Godzio
 */
public class CurrentPercentageLoadMatcher extends TypeSafeMatcher<Server> {

    private final double expectedLoad;

    public CurrentPercentageLoadMatcher( double expectedLoad ) {
        this.expectedLoad = expectedLoad;
    }

    @Override
    protected boolean matchesSafely( Server server ) {
        return Math.abs( server.hasCurrentPercentageLoad() - expectedLoad ) < 0.01d;
    }

    public void describeTo( Description d ) {
    }

}
