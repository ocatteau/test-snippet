package fr.stateofmind.test.mockito;

import static org.junit.Assert.*;

import org.junit.*;
import org.junit.runner.*;
import org.mockito.*;
import org.mockito.runners.*;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class MockitoAnnotationTest {
    @Mock private List<String> list;
    @InjectMocks private MyObject object = new MyObject();

    @Test public void function() throws Exception {
        assertNotNull(object.getList());
        assertSame(list, object.getList());
    }
}
