/**
 * Copyright (c) 2007 State Of Mind.
 */
package fr.stateofmind.test.junit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class JUnit4UseOfTestCase extends JUnit4TestCase {

    @BeforeClass
    public static void beforeClass1() {
        System.out.println("JUnit4UseOfTestCase.beforeClass1");
    }


    @BeforeClass
    public static void beforeClass2() {
        System.out.println("JUnit4UseOfTestCase.beforeClass2");
    }


    @AfterClass
    public static void afterClass1() {
        System.out.println("JUnit4UseOfTestCase.afterClass1");
    }


    @AfterClass
    public static void afterClass2() {
        System.out.println("JUnit4UseOfTestCase.afterClass2");
    }


    @Before
    public void before1() {
        System.out.println("JUnit4UseOfTestCase.before1");
    }


    @Before
    public void before2() {
        System.out.println("JUnit4UseOfTestCase.before2");
    }


    @After
    public void after1() {
        System.out.println("JUnit4UseOfTestCase.after1");
    }


    @After
    public void after2() {
        System.out.println("JUnit4UseOfTestCase.after2");
    }


    @Test
    public void useOfTestCase() {
    }
}
