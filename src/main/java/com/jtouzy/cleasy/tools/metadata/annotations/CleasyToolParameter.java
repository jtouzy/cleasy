package com.jtouzy.cleasy.tools.metadata.annotations;

public @interface CleasyToolParameter {
    String id();
    String shortId();
    String description();
    boolean required() default false;
}
