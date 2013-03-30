package fr.stateofmind.test.jmockit.domain;

import fr.stateofmind.test.jmockit.infrastructure.Database;
import mockit.Expectations;
import mockit.Mocked;
import mockit.NonStrict;
import mockit.NonStrictExpectations;
import mockit.UsingMocksAndStubs;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.junit.*;

import java.util.ArrayList;
import java.util.List;


@UsingMocksAndStubs(Database.class)
// TODO: stub out static blocks by default in @Mocked classes
public final class ServiceA_ExpectationsAPI_Test {
    @Mocked
    final Database unused = null;
    @NonStrict
    SimpleEmail email;

    @Test
    public void doBusinessOperationXyz() throws Exception {
        final EntityX data = new EntityX(5, "abc", "5453-1");
        final List<EntityX> items = new ArrayList<EntityX>();
        items.add(new EntityX(1, "AX5", "someone@somewhere.com"));

        new Expectations() {
            {
                Database.find(withSubstring("select"), (Object[]) null);
                result = items;
                Database.persist(data);
                email.send();
            }
        };

        new MyBusinessService().doBusinessOperationXyz(data);
    }

    @Test(expected = EmailException.class)
    public void doBusinessOperationXyzWithInvalidEmailAddress() throws Exception {
        new NonStrictExpectations() {
            {
                email.addTo((String) withNotNull());
                result = new EmailException();
            }
        };

        EntityX data = new EntityX(5, "abc", "5453-1");
        new MyBusinessService().doBusinessOperationXyz(data);
    }
}
