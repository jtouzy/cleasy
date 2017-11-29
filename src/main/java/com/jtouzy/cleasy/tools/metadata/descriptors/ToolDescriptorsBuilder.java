package com.jtouzy.cleasy.tools.metadata.descriptors;

import com.jtouzy.cleasy.tools.metadata.annotations.CleasyTool;
import com.jtouzy.cleasy.tools.metadata.annotations.CleasyToolParameter;
import com.jtouzy.cleasy.utils.ClassUtils;

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
        Iterable<Class<?>> classes = ClassUtils.getClassesAnnotatedWith(CleasyTool.class, scanPackage);
        if (!classes.iterator().hasNext()) {
            throw new ToolDescriptionException("No tool description found in classpath.");
        } else {
            return buildDescriptors(classes);
        }
    }

    private static final List<ToolDescriptor> buildDescriptors(Iterable<Class<?>> annotatedClasses) {
        List<ToolDescriptor> descriptors = new ArrayList<>();
        Iterator<Class<?>> it = annotatedClasses.iterator();
        List<String> idList = new ArrayList<>();
        List<String> shortIdList = new ArrayList<>();
        String id;
        String shortId;
        Class<?> clazz;
        ToolDescriptor toolDescriptor;
        CleasyTool toolAnnotation;
        while (it.hasNext()) {
            clazz = it.next();
            toolAnnotation = clazz.getAnnotation(CleasyTool.class);
            id = toolAnnotation.id();
            shortId = toolAnnotation.shortId();
            if (idList.contains(id)) {
                throw new ToolDescriptionException("Multiple tools have the same id [" + id + "]");
            }
            idList.add(id);
            if (shortIdList.contains(shortId)) {
                throw new ToolDescriptionException("Multiple tools have the same short-id [" + shortId + "]");
            }
            shortIdList.add(shortId);
            toolDescriptor = new ToolDescriptor(id, shortId, toolAnnotation.description(), clazz);
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
                    parameterAnnotation.id(), parameterAnnotation.shortId(),
                    parameterAnnotation.description(), parameterAnnotation.required()));
        }
        return toolDescriptor;
    }
}
