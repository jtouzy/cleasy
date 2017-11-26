package com.jtouzy.cleasy.utils;

import org.atteo.classindex.ClassFilter;
import org.atteo.classindex.ClassIndex;

import java.lang.annotation.Annotation;

public final class ClassUtils {
    public static <T extends Annotation> Iterable<Class<?>> getClassesAnnotatedWith(Class<T> annotation, String scanPackage) {
        Iterable<Class<?>> classes = ClassIndex.getAnnotated(annotation);
        if (scanPackage != null) {
            classes = ClassFilter.any(e -> e.getPackage().getName().startsWith(scanPackage)).from(classes);
        }
        return classes;
    }
}
