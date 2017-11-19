package com.jtouzy.cleasy.tools.metadata.annotations;

import com.jtouzy.cleasy.tools.execution.Executor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CleasyTool {
    String id();
    String shortId();
    String description();
    CleasyToolParameter[] parameters() default {};
    Class<? extends Executor> executor();
}
