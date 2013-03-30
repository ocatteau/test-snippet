/**
 * Copyright (c) 2007 State Of Mind.
 */
package fr.stateofmind.test.junit;

import org.junit.Assert;
import org.junit.Test;

public class SingletonSecondTest {

    @Test
    public void test_methodA() throws Exception {
        Assert.assertNotNull(Singleton.getInstance());
    }


    @Test
    public void test_methodB() throws Exception {
        Assert.assertNotNull(Singleton.getInstance());
    }
}