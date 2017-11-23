package com.jtouzy.cleasy.tests.cli;

import com.jtouzy.cleasy.cli.CLProcessing;
import com.jtouzy.cleasy.cli.CLProcessingException;
import com.jtouzy.cleasy.cli.CommandDescription;
import com.jtouzy.cleasy.configuration.DefaultConfiguration;
import com.jtouzy.cleasy.launch.LaunchContext;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CLProcessingTests {

    // Utility functions for testing

    private LaunchContext getDefaultContext() {
        return new LaunchContext(new DefaultConfiguration(), Collections.emptyList());
    }

    private CommandDescription process(String args[])
    throws CLProcessingException {
        CLProcessing processor = new CLProcessing(getDefaultContext());
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
        process(toArray(Arrays.asList("toolName", "error")));
    }

    @Test
    public void testCountWithOneSimpleArgument() throws CLProcessingException {
        CommandDescription result = process(toArray(Arrays.asList("toolName", "-t", "t_value")));
        Assert.assertEquals(1, result.getParameters().keySet().size());
    }

    @Test
    public void testWithOneSimpleArgument() throws CLProcessingException {
        CommandDescription result = process(toArray(Arrays.asList("toolName", "-t", "t_value")));
        Assert.assertEquals("t", result.getParameters().keySet().iterator().next());
        Assert.assertEquals("t_value", result.getParameters().get("t"));
    }

    // Multiple arguments tests

    @Test(expected = CLProcessingException.class)
    public void testWithFirstToolAndOneSimpleArgumentAndSecondArgumentWithNoParameterName() throws CLProcessingException {
        process(toArray(Arrays.asList("toolName", "-t", "t_value", "error")));
    }
}
