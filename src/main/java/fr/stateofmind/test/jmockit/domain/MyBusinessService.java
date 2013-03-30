package fr.stateofmind.test.jmockit.domain;

import fr.stateofmind.test.jmockit.infrastructure.Database;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import java.math.BigDecimal;
import java.util.List;

public final class MyBusinessService {
    // This method can easily be made to be transactional, so that any exception its execution throws
    // would cause a rollback (assuming a transaction gets started in the first place) somewhere up
    // in the call stack.
    public void doBusinessOperationXyz(EntityX data) throws EmailException {
        // Locate existing persistent entities of the same entity type (note that the query string is
        // a DSL for querying persistent domain entities, written in terms of the domain, not in terms
        // of relational tables and columns):
        List<EntityX> items =
                Database.find("select item from EntityY item where item.someProperty=?", data.getSomeProperty());

        // Compute or obtain from another service a total value for the new persistent entity:
        BigDecimal total = new BigDecimal("12.30");
        data.setTotal(total);

        // Persist the entity (no DAO required for such a common, high-level, operation):
        Database.persist(data);

        sendNotificationEmailToCustomer(data, items);
    }

    private void sendNotificationEmailToCustomer(EntityX data, List<EntityX> items)
            throws EmailException {
        Email email = new SimpleEmail();
        email.setSubject("Notification about processing of ...");
        email.addTo(data.getCustomerEmail());

        // Other e-mail parameters, such as the host name of the mail server, have defaults defined
        // through external configuration.

        String message = buildNotificationMessage(items);
        email.setMsg(message);

        email.send();
    }

    private String buildNotificationMessage(List<EntityX> items) {
        StringBuilder message = new StringBuilder();

        for (EntityX item : items) {
            message.append(item.getSomeProperty()).append(" Total is: ").append(item.getTotal());
        }

        return message.toString();
    }
}

