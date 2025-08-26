package com.ratanak.demo2.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ElementType.FIELD,ElementType.TYPE_PARAMETER})
public @interface ValidEnum {
}
