package fr.stateofmind.test.jmockit.domain;

import fr.stateofmind.test.jmockit.infrastructure.Database;
import mockit.Mock;
import mockit.MockClass;
import mockit.MockUp;
import mockit.UsingMocksAndStubs;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.junit.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@UsingMocksAndStubs({MyBusinessService_CoreAPI_Test.MockDatabase.class, Email.class})
public final class ServiceA_AnnotationsAPI_Test {
    @MockClass(realClass = Database.class, stubs = "<clinit>")
    public static class MockDatabase {
        @Mock(invocations = 1)
        public static List<EntityX> find(String ql, Object... args) {
            assertNotNull(ql);
            assertTrue(args.length > 0);
            return Arrays.asList(new EntityX(1, "AX5", "someone@somewhere.com"));
        }

        @Mock(maxInvocations = 1)
        public static void persist(Object o) {
            assertNotNull(o);
        }
    }

    final EntityX data = new EntityX(5, "abc", "5453-1");

    @Test
    public void doBusinessOperationXyz() throws Exception {
        new MockUp<Email>() {
            @Mock(invocations = 1)
            String send() {
                return "";
            }
        };

        new MyBusinessService().doBusinessOperationXyz(data);
    }

    @Test(expected = EmailException.class)
    public void doBusinessOperationXyzWithInvalidEmailAddress() throws Exception {
        new MockUp<Email>() {
            @Mock
            Email addTo(String emailAddress) throws EmailException {
                assertNotNull(emailAddress);
                throw new EmailException();
            }
        };

        new MyBusinessService().doBusinessOperationXyz(data);
    }
}
