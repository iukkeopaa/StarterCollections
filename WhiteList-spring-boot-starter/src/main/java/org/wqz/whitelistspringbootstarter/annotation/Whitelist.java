package org.wqz.whitelistspringbootstarter.annotation;


import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface Whitelist {

    String[] value() default "";


}
