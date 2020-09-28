package com.codecool.customannotations.webroute;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface WebRoute {
        String value();
}
