/**
 * Copyright (c) 2007 State Of Mind.
 */
package fr.stateofmind.test.junit.rule.custom;

import java.util.Random;
import org.junit.Rule;
import org.junit.Test;

public final class ConcurrentThrowingTest {

    @Rule
    public ConcurrentThrowingRule concurrentRule = new ConcurrentThrowingRule();


    @Test(expected = NumberFormatException.class)
    @Concurrent(10)
    public void myTestMethod_failing() throws InterruptedException {
        System.out.println("Thread " + Thread.currentThread().getName() + " started !");
        int n = new Random().nextInt(5000);
        System.out.println("Thread " + Thread.currentThread().getName() + " wait " + n + "ms");
        Thread.sleep(n);
        Integer.parseInt("blabla");
    }
}