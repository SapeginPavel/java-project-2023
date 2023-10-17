package com.sapegin.dependencies.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) //работает в рантайме
@Target(ElementType.FIELD) //потому что будем писать её над атрибутами
public @interface Inject {
}
