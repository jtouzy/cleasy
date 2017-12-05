package com.jtouzy.cleasy.tests.tools.metadata.descriptors.classes.paramMultipleSameId;

import com.jtouzy.cleasy.tools.metadata.annotations.CleasyTool;
import com.jtouzy.cleasy.tools.metadata.annotations.CleasyToolParameter;

@CleasyTool(
    id = "basic",
    shortId = "basic",
    description = "Basic description",
    parameters = {
        @CleasyToolParameter(id = "parameter", shortId = "short_parameter", description = "parameter_description"),
        @CleasyToolParameter(id = "parameter", shortId = "short_parameter", description = "parameter_description")
    }
)
public class ToolWithSameParameterId {
}
