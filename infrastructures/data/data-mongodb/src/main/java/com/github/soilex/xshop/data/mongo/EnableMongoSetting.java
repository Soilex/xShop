package com.github.soilex.xshop.data.mongo;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(MongoAutoConfiguration.class)
public @interface EnableMongoSetting{
}
