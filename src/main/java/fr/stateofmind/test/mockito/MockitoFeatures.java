/**
 * Copyright (c) 2007 State Of Mind.
 */
package fr.stateofmind.test.mockito;

import java.util.List;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import org.junit.Test;
import org.mockito.InOrder;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

public class MockitoFeatures {

    @Test
    public void verifiyInteractions() throws Exception {
        List<String> mockedList = mock(List.class);

        mockedList.add("one");
        mockedList.clear();

        verify(mockedList).add("one");
        verify(mockedList).clear();
    }


    @Test
    public void stubMethodCall() throws Exception {
        List<String> mockedList = mock(List.class);

        when(mockedList.get(0)).thenReturn("first");

        assertThat(mockedList.get(0), equalTo("first"));
        assertThat(mockedList.get(1), nullValue());

        verify(mockedList).get(0);
        verify(mockedList).get(1);
    }


    @Test
    public void stubMethodCallWithAny() throws Exception {
        List<String> mockedList = mock(List.class);

        when(mockedList.get(anyInt())).thenReturn("any");

        assertThat(mockedList.get(anyInt()), equalTo("any"));

        verify(mockedList).get(anyInt());
    }


    @Test
    public void numberOfInvocations() throws Exception {
        List<String> mockedList = mock(List.class);

        mockedList.add("once");

        mockedList.add("twice");
        mockedList.add("twice");

        // Same behaviour, times(1) is the default value
        verify(mockedList).add("once");
        verify(mockedList, times(1)).add("once");

        verify(mockedList, times(2)).add("twice");

        verify(mockedList, never()).add("never happened");
    }


    @Test(expected = RuntimeException.class)
    public void stubMethodCallWithException() throws Exception {
        List<String> mockedList = mock(List.class);

        doThrow(new RuntimeException()).when(mockedList).clear();

        mockedList.clear();
    }


    @Test
    public void verificationOrder() throws Exception {
        List<String> firstMock = mock(List.class);
        List<String> secondMock = mock(List.class);

        firstMock.add("first");
        secondMock.add("second");

        InOrder inOrder = inOrder(firstMock, secondMock);

        inOrder.verify(firstMock).add("first");
        inOrder.verify(secondMock).add("second");
    }


    @Test
    public void consecutiveCalls() throws Exception {
        AnObject mockedObject = mock(AnObject.class);

        when(mockedObject.someMethod("some arg"))
              .thenThrow(new RuntimeException())
              .thenReturn("one", "two");

        try {
            mockedObject.someMethod("some arg");
            fail("should fail");
        }
        catch (RuntimeException e) {
            // OK
        }
        assertThat(mockedObject
              .someMethod("some arg"), equalTo("one"));
        assertThat(mockedObject.someMethod("some arg"), equalTo("two"));
        assertThat(mockedObject.someMethod("some arg"), equalTo("two"));
    }


    private class AnObject {

        public String someMethod(String arg) {
            return "default";
        }
    }
}
