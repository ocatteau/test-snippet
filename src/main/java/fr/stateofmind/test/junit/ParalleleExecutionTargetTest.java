/**
 * Copyright (c) 2007 State Of Mind.
 */
package fr.stateofmind.test.junit;

import org.junit.Test;

public class ParalleleExecutionTargetTest {
    @Test
    public void one() throws InterruptedException {
        Thread.sleep(1000);
    }


    @Test
    public void two() throws InterruptedException {
        Thread.sleep(1000);
    }
}
