package fr.stateofmind.test.jmockit.domain;

import fr.stateofmind.test.jmockit.infrastructure.Database;
import mockit.Capturing;
import mockit.Mocked;
import mockit.NonStrictExpectations;
import mockit.UsingMocksAndStubs;
import mockit.Verifications;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.junit.*;

import java.util.Arrays;
import java.util.List;

@UsingMocksAndStubs(Database.class)
public final class ServiceA_VerificationsAPI_Test {
    @Mocked
    Database onlyStatics;
    @Capturing
    Email email; // concrete subclass mocked on demand, when loaded

    @Test
    public void doBusinessOperationXyzPersistsData() throws Exception {
        final EntityX data = new EntityX(5, "abc", "5453-1");

        // No expectations recorded in this case.

        new MyBusinessService().doBusinessOperationXyz(data);

        new Verifications() {
            {
                Database.persist(data);
            }
        };
    }

    @Test
    public void doBusinessOperationXyzFindsItemsAndSendsNotificationEmail() throws Exception {
        EntityX data = new EntityX(5, "abc", "5453-1");
        final List<EntityX> items = Arrays.asList(new EntityX(1, "AX5", "someone@somewhere.com"));

        // Invocations that produce a result are recorded, but only those we care about.
        new NonStrictExpectations() {
            {
                Database.find(withSubstring("select"), (Object[]) null);
                result = items;
            }
        };

        new MyBusinessService().doBusinessOperationXyz(data);

        new Verifications() {
            {
                email.send();
            }
        };
    }

    @Test(expected = EmailException.class)
    public void doBusinessOperationXyzWithInvalidItemStatus() throws Exception {
        new NonStrictExpectations() {
            {
                email.addTo((String) withNotNull());
                result = new EmailException();
            }
        };

        EntityX data = new EntityX(5, "abc", "5453-1");
        new MyBusinessService().doBusinessOperationXyz(data);

        // Nothing left to verify at this point.
    }
}

