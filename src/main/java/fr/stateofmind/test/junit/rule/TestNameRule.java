/**
 * Copyright (c) 2007 State Of Mind.
 */
package fr.stateofmind.test.junit.rule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import static org.junit.Assert.*;

public class TestNameRule {
    @Rule
    public TestName test = new TestName();


    @Test
    public void testA() {
        assertEquals("testA", test.getMethodName());
    }
}
