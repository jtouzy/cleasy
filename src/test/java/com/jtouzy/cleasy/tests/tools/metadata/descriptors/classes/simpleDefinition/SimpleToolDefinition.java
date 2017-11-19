package com.jtouzy.cleasy.tests.tools.metadata.descriptors.classes.simpleDefinition;

import com.jtouzy.cleasy.tools.metadata.annotations.CleasyTool;
import com.jtouzy.cleasy.tools.metadata.annotations.CleasyToolParameter;

@CleasyTool(
        id = "test",
        shortId = "short_test",
        description = "test_description",
        parameters = {
                @CleasyToolParameter(id = "parameter", shortId = "short_parameter", description = "parameter_description")
        },
        executor = SimpleToolExecutor.class
)
public class SimpleToolDefinition {
}
