package com.jtouzy.cleasy.tools.metadata.descriptors;

import com.jtouzy.cleasy.tools.metadata.annotations.CleasyTool;
import com.jtouzy.cleasy.tools.metadata.annotations.CleasyToolParameter;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ToolDescriptorsBuilder {
    private static String scanPackage;

    public static final void setScanPackage(String scanPackage) {
        ToolDescriptorsBuilder.scanPackage = scanPackage;
    }

    public static final List<ToolDescriptor> build() {
        Reflections annotationsFinder = new Reflections(scanPackage);
        Set<Class<?>> classes = annotationsFinder.getTypesAnnotatedWith(CleasyTool.class);
        if (classes.isEmpty()) {
            throw new ToolDescriptionException("No tool description found in classpath.");
        } else {
            return buildDescriptors(classes);
        }
    }

    private static final List<ToolDescriptor> buildDescriptors(Set<Class<?>> annotatedClasses) {
        List<ToolDescriptor> descriptors = new ArrayList<>();
        Iterator<Class<?>> it = annotatedClasses.iterator();
        Class<?> clazz;
        ToolDescriptor toolDescriptor;
        while (it.hasNext()) {
            clazz = it.next();
            CleasyTool toolAnnotation = clazz.getAnnotation(CleasyTool.class);
            toolDescriptor = new ToolDescriptor(
                    toolAnnotation.id(), toolAnnotation.shortId(),
                    toolAnnotation.description(), toolAnnotation.executor()
            );
            descriptors.add(appendParameterDescriptorsIn(toolDescriptor, toolAnnotation));
        }
        return descriptors;
    }

    private static final ToolDescriptor appendParameterDescriptorsIn(ToolDescriptor toolDescriptor, CleasyTool annotation) {
        Iterator<CleasyToolParameter> it = Arrays.asList(annotation.parameters()).iterator();
        CleasyToolParameter parameterAnnotation;
        while (it.hasNext()) {
            parameterAnnotation = it.next();
            toolDescriptor.addParameter(new ParameterDescriptor(
                    parameterAnnotation.id(), parameterAnnotation.shortId(), parameterAnnotation.description()));
        }
        return toolDescriptor;
    }
}
