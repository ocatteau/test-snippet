/**
 * Copyright (c) 2007 State Of Mind.
 */
package fr.stateofmind.test.junit;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.AfterClass;

public abstract class JUnit4TestCase {

    @BeforeClass
    public static void setUpClass() {
        System.out.println("JUnit4TestCase.setUpClass");
    }


    @AfterClass
    public static void tearDownClass() {
        System.out.println("JUnit4TestCase.tearDownClass");
    }


    @Before
    public final void setUp() {
        System.out.println("JUnit4TestCase.setUp");
    }


    @After
    public final void tearDown() {
        System.out.println("JUnit4TestCase.tearDown");
    }
}
