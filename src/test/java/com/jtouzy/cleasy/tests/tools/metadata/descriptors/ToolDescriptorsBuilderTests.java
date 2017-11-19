package com.jtouzy.cleasy.tests.tools.metadata.descriptors;

import com.jtouzy.cleasy.tests.tools.metadata.descriptors.classes.simpleDefinition.SimpleToolDefinition;
import com.jtouzy.cleasy.tools.metadata.annotations.CleasyTool;
import com.jtouzy.cleasy.tools.metadata.annotations.CleasyToolParameter;
import com.jtouzy.cleasy.tools.metadata.descriptors.ToolDescriptionException;
import com.jtouzy.cleasy.tools.metadata.descriptors.ToolDescriptor;
import com.jtouzy.cleasy.tools.metadata.descriptors.ToolDescriptorsBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ToolDescriptorsBuilderTests {
    private static final String testClasses = "com.jtouzy.cleasy.tests.tools.metadata.descriptors.classes";

    public CleasyTool getSimpleToolDefinitionAnnotation() {
        return SimpleToolDefinition.class.getAnnotation(CleasyTool.class);
    }

    public ToolDescriptor getSimpleToolDefinition() {
        ToolDescriptorsBuilder.setScanPackage(testClasses + ".simpleDefinition");
        List<ToolDescriptor> descriptors = ToolDescriptorsBuilder.build();
        if (descriptors.isEmpty() || descriptors.size() != 1) {
            Assert.fail("Wrong test case : This should be returning one tool only");
        }
        return descriptors.get(0);
    }

    @Test
    public void testWithNoToolsProvided() {
        try {
            ToolDescriptorsBuilder.setScanPackage(testClasses + ".noToolsPackage");
            ToolDescriptorsBuilder.build();
            Assert.fail("ToolDescriptionException should be thrown");
        } catch (ToolDescriptionException ex) {
            Assert.assertEquals("No tool description found in classpath.", ex.getMessage());
        }
    }

    @Test
    public void testWithSimpleDefinitionExpectedId() {
        CleasyTool annotation = getSimpleToolDefinitionAnnotation();
        ToolDescriptor descriptor = getSimpleToolDefinition();
        Assert.assertEquals(annotation.id(), descriptor.getId());
    }

    @Test
    public void testWithSimpleDefinitionExpectedShortId() {
        CleasyTool annotation = getSimpleToolDefinitionAnnotation();
        ToolDescriptor descriptor = getSimpleToolDefinition();
        Assert.assertEquals(annotation.shortId(), descriptor.getShortId());
    }

    @Test
    public void testWithSimpleDefinitionExpectedDescription() {
        CleasyTool annotation = getSimpleToolDefinitionAnnotation();
        ToolDescriptor descriptor = getSimpleToolDefinition();
        Assert.assertEquals(annotation.description(), descriptor.getDescription());
    }

    @Test
    public void testWithSimpleDefinitionExpectedParametersCount() {
        CleasyTool annotation = getSimpleToolDefinitionAnnotation();
        ToolDescriptor descriptor = getSimpleToolDefinition();
        Assert.assertEquals(annotation.parameters().length, descriptor.getParametersCount());
    }

    @Test
    public void testWithSimpleDefinitionExpectedFirstParameterId() {
        CleasyTool annotation = getSimpleToolDefinitionAnnotation();
        CleasyToolParameter parameterAnnotation = annotation.parameters()[0];
        ToolDescriptor descriptor = getSimpleToolDefinition();
        Assert.assertEquals(parameterAnnotation.id(), descriptor.getParametersIterator().next().getId());
    }

    @Test
    public void testWithSimpleDefinitionExpectedFirstParameterShortId() {
        CleasyTool annotation = getSimpleToolDefinitionAnnotation();
        CleasyToolParameter parameterAnnotation = annotation.parameters()[0];
        ToolDescriptor descriptor = getSimpleToolDefinition();
        Assert.assertEquals(parameterAnnotation.shortId(), descriptor.getParametersIterator().next().getShortId());
    }

    @Test
    public void testWithSimpleDefinitionExpectedFirstParameterDescription() {
        CleasyTool annotation = getSimpleToolDefinitionAnnotation();
        CleasyToolParameter parameterAnnotation = annotation.parameters()[0];
        ToolDescriptor descriptor = getSimpleToolDefinition();
        Assert.assertEquals(parameterAnnotation.description(), descriptor.getParametersIterator().next().getDescription());
    }
}
