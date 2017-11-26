package com.jtouzy.cleasy.tools.metadata.descriptors;

import com.jtouzy.cleasy.tools.metadata.annotations.CleasyTool;
import com.jtouzy.cleasy.tools.metadata.annotations.CleasyToolParameter;
import org.atteo.classindex.ClassIndex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ToolDescriptorsBuilder {
    private static String scanPackage;

    public static final void setScanPackage(String scanPackage) {
        ToolDescriptorsBuilder.scanPackage = scanPackage;
    }

    public static final List<ToolDescriptor> build() {
        Iterable<Class<?>> classes = ClassIndex.getAnnotated(CleasyTool.class);
        if (!classes.iterator().hasNext()) {
            throw new ToolDescriptionException("No tool description found in classpath.");
        } else {
            return buildDescriptors(classes);
        }
    }

    private static final List<ToolDescriptor> buildDescriptors(Iterable<Class<?>> annotatedClasses) {
        List<ToolDescriptor> descriptors = new ArrayList<>();
        Iterator<Class<?>> it = annotatedClasses.iterator();
        Class<?> clazz;
        ToolDescriptor toolDescriptor;
        while (it.hasNext()) {
            clazz = it.next();
            CleasyTool toolAnnotation = clazz.getAnnotation(CleasyTool.class);
            toolDescriptor = new ToolDescriptor(
                toolAnnotation.id(), toolAnnotation.shortId(),
                toolAnnotation.description(), clazz
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
