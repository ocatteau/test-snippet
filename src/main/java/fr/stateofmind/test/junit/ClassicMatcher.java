/**
 * Copyright (c) 2007 State Of Mind.
 */
package fr.stateofmind.test.junit;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.junit.internal.matchers.IsCollectionContaining.*;
import static org.junit.internal.matchers.StringContains.*;

public class ClassicMatcher {
    @Test
    public void simpleMatcher() {
        assertThat("Pierre", equalTo("Pierre"));
    }


    @Test
    public void mixedMatcher() {
        assertThat("Pierre", not(equalTo("Paul")));
    }


    @Test
    public void collectionMatcher() {
        List<String> list = Arrays.asList("Pierre", "Paul");
        assertThat(list, hasItem("Paul"));
        assertThat(list, hasItem(containsString("au")));
    }
}
