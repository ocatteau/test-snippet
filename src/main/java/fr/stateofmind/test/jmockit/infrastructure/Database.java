/**
 * Copyright (c) 2007 State Of Mind.
 */
package fr.stateofmind.test.jmockit.infrastructure;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public final class Database {
    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("AppPersistenceUnit");
    private static final ThreadLocal<EntityManager> workUnit = new ThreadLocal<EntityManager>();

    private Database() {
    }

    public static <E> E find(Class<E> entityClass, Object entityId) {
        E entity = workUnit().find(entityClass, entityId);
        return entity;
    }

    public static <E> List<E> find(String ql, Object... args) {
        Query query = workUnit().createQuery(ql);
        int position = 1;

        for (Object arg : args) {
            query.setParameter(position, arg);
            position++;
        }

        return (List<E>) query.getResultList();
    }

    public static void persist(Object data) {
        // Persist the data of a given transient domain entity object, using JPA.
        // (In a web app, this could be scoped to the HTTP request/response cycle, which normally runs
        // entirely in a single thread - a custom javax.servlet.Filter could close the thread-bound
        // EntityManager.)
        workUnit().persist(data);
    }

    public static void remove(Object persistentEntity) {
        workUnit().remove(persistentEntity);
    }

    private static EntityManager workUnit() {
        EntityManager wu = workUnit.get();

        if (wu == null) {
            wu = entityManagerFactory.createEntityManager();
            workUnit.set(wu);
        }

        return wu;
    }
}

