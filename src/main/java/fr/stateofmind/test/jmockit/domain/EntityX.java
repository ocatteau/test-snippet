/**
 * Copyright (c) 2007 State Of Mind.
 */
package fr.stateofmind.test.jmockit.domain;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class EntityX {
    private String customerEmail;
    private BigDecimal total;

    public EntityX() {
    }

    public EntityX(int type, String code, String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getSomeProperty() {
        return "abc";
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
