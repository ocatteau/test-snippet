/**
 * Copyright (c) 2007 State Of Mind.
 */
package fr.stateofmind.test.junit;

import org.apache.log4j.Logger;

public class Singleton {
    private static final Logger LOG = Logger.getLogger(Singleton.class);

    private static Singleton instance;


    private Singleton() {
        LOG.info("Singleton created.");
    }


    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        LOG.info("Singleton.getInstance().");
        return instance;
    }
}
