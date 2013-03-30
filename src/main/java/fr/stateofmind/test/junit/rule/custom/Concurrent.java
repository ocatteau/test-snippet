/**
 * Copyright (c) 2007 State Of Mind.
 */
package fr.stateofmind.test.junit.rule.custom;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Concurrent {
    int value() default 10;
}