/**
 * Copyright (c) 2007 State Of Mind.
 */
package fr.stateofmind.test.junit;

import java.util.Arrays;
import java.util.List;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import static org.junit.Assert.*;
import org.junit.Test;
import static org.junit.internal.matchers.Each.*;
import static org.junit.internal.matchers.IsCollectionContaining.*;
import static org.junit.internal.matchers.StringContains.*;

public class CustomMatcher {

    @Test
    public void collectionMatcher() {
        List<String> list = Arrays.asList("Pierre", "Paul");
        assertThat(list, hasItem("Paul"));
        assertThat(list, hasItem(containsString("au")));
    }


    @Test
    public void hasItemWithStringLengthMatcher() throws Exception {
        List<String> list = Arrays.asList("notThreeLength", "foo");
        assertThat(list, hasItem(stringLength(3)));
    }


    @Test
    public void eachItemWithStringLengthMatcher() throws Exception {
        List<String> list = Arrays.asList("foo", "bar");
        assertThat(list, each(stringLength(3)));
    }


    private StringLengthMatcher stringLength(int length) {
        return new StringLengthMatcher(length);
    }


    public static class StringLengthMatcher extends BaseMatcher<String> {
        private final int expected;


        public StringLengthMatcher(int expected) {
            this.expected = expected;
        }


        public boolean matches(Object object) {
            return expected == ((String)object).length();
        }


        public void describeTo(Description description) {
            description.appendText("a String with length ").appendText("" + expected);
        }
    }
}
