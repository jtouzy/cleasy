package com.jtouzy.cleasy.processing;

import com.google.common.collect.Lists;
import com.jtouzy.cleasy.configuration.Configuration;
import com.jtouzy.cleasy.configuration.DefaultConfiguration;
import com.jtouzy.cleasy.metadata.CommandDescription;
import com.jtouzy.cleasy.processing.cli.CLProcessing;
import com.jtouzy.cleasy.processing.cli.CLProcessingException;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

public class CLProcessingTests {

    // Utility functions for testing

    private Configuration getDefaultContextDescriptor() {
        return new DefaultConfiguration();
    }

    private CommandDescription process(String args[])
    throws CLProcessingException {
        CLProcessing processor = new CLProcessing(getDefaultContextDescriptor());
        return processor.process(args);
    }

    private String[] toArray(List<String> list) {
        return list.stream().toArray(String[]::new);
    }

    // No arguments tests

    @Test(expected = CLProcessingException.class)
    public void testWithEmptyArguments() throws CLProcessingException {
        process(toArray(Collections.emptyList()));
    }

    // One argument tests (without param name - count - one simple OK)

    @Test(expected = CLProcessingException.class)
    public void testWithFirstToolAndOneArgumentWithNoParameterName() throws CLProcessingException {
        process(toArray(Lists.newArrayList("toolName", "error")));
    }

    @Test
    public void testCountWithOneSimpleArgument() throws CLProcessingException {
        CommandDescription result = process(toArray(Lists.newArrayList("toolName", "-t", "t_value")));
        Assert.assertEquals(1, result.getParameters().keySet().size());
    }

    @Test
    public void testWithOneSimpleArgument() throws CLProcessingException {
        CommandDescription result = process(toArray(Lists.newArrayList("toolName", "-t", "t_value")));
        Assert.assertEquals("t", result.getParameters().keySet().iterator().next());
        Assert.assertEquals("t_value", result.getParameters().get("t"));
    }

    // Multiple arguments tests

    @Test(expected = CLProcessingException.class)
    public void testWithFirstToolAndOneSimpleArgumentAndSecondArgumentWithNoParameterName() throws CLProcessingException {
        process(toArray(Lists.newArrayList("toolName", "-t", "t_value", "error")));
    }
}
