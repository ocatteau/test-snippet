/**
 * Copyright (c) 2007 State Of Mind.
 */
package fr.stateofmind.test.junit;

import org.junit.Test;
import org.junit.experimental.ParallelComputer;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import static org.junit.Assert.*;

public class ParalleleExecutionTest {
    @Test
    public void testsRunInParallel() {
        long start = System.currentTimeMillis();
        Result result = JUnitCore.runClasses(ParallelComputer.methods(), ParalleleExecutionTargetTest.class);
        assertTrue(result.wasSuccessful());
        long end = System.currentTimeMillis();
        System.out.println("duration = " + (end - start));
        //assertThat(end - start, betweenInclusive(1000, 1500));
    }
}
