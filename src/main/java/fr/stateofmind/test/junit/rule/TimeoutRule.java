/**
 * Copyright (c) 2007 State Of Mind.
 */
package fr.stateofmind.test.junit.rule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import static org.junit.Assert.*;

public class TimeoutRule {
    @Rule
    public Timeout test = new Timeout(10);


    @Test
    public void testA() throws Exception {
        Thread.sleep(200);
        assertEquals("testA", "testA");
    }
}