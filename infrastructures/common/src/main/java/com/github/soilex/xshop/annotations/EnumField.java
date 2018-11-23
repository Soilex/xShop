package com.github.soilex.xshop.annotations;


import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EnumField {
    String text() default "";

    int value() default 0;
}