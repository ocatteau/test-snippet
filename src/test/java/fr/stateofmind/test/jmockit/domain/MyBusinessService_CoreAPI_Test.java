package fr.stateofmind.test.jmockit.domain;

import fr.stateofmind.test.jmockit.infrastructure.Database;
import junit.framework.TestCase;
import mockit.Mockit;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;

import java.util.Arrays;
import java.util.List;

public final class MyBusinessService_CoreAPI_Test extends TestCase {
    private boolean emailSent;

    public static class MockDatabase {
        static int findMethodCallCount;
        static int persistMethodCallCount;

        public static List<EntityX> find(String ql, Object... args) {
            assertNotNull(ql);
            assertTrue(args.length > 0);
            findMethodCallCount++;
            return Arrays.asList(new EntityX(1, "AX5", "someone@somewhere.com"));
        }

        public static void persist(Object o) {
            assertNotNull(o);
            persistMethodCallCount++;
        }
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        Mockit.stubOut(Database.class, Email.class);

        MockDatabase.findMethodCallCount = 0;
        MockDatabase.persistMethodCallCount = 0;
        Mockit.redefineMethods(Database.class, MockDatabase.class);
    }

    @Override
    protected void tearDown() throws Exception {
        Mockit.restoreAllOriginalDefinitions();
        super.tearDown();
    }

    public void testDoBusinessOperationXyz() throws Exception {
        Mockit.redefineMethods(Email.class, new MockEmail(true));

        EntityX data = new EntityX(5, "abc", "5453-1");

        new MyBusinessService().doBusinessOperationXyz(data);

        assertEquals(1, MockDatabase.findMethodCallCount);
        assertEquals(1, MockDatabase.persistMethodCallCount);
        assertTrue(emailSent);
    }

    public class MockEmail {
        final boolean addToShouldSucceed;
        public Email it;

        MockEmail(boolean addToShouldSucceed) {
            this.addToShouldSucceed = addToShouldSucceed;
        }

        public Email addTo(String emailAddress) throws EmailException {
            assertNotNull(emailAddress);

            if (addToShouldSucceed) {
                return it;
            } else {
                throw new EmailException();
            }
        }

        public String send() {
            emailSent = true;
            return "";
        }
    }

    public void testDoBusinessOperationXyzWithInvalidCustomerEmailAddress() throws Exception {
        Mockit.redefineMethods(Email.class, new MockEmail(false));

        EntityX data = new EntityX(5, "abc", "5453-1");

        try {
            new MyBusinessService().doBusinessOperationXyz(data);
            fail(EmailException.class + " was expected");
        }
        catch (EmailException ignore) {
            // OK, test passed
            assertFalse(emailSent);
        }
    }
}
