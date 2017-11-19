package com.jtouzy.cleasy.tools.execution;

import com.jtouzy.cleasy.configuration.Configuration;
import com.jtouzy.cleasy.tools.metadata.descriptors.ToolDescriptor;

import java.util.Map;
import java.util.Optional;

public class ExecutionContext {
    private Configuration configuration;
    private ToolDescriptor toolDescriptor;
    private Map<String, String> parameters;

    public ExecutionContext(Configuration configuration, ToolDescriptor toolDescriptor, Map<String, String> parameters) {
        this.configuration = configuration;
        this.toolDescriptor = toolDescriptor;
        this.parameters = parameters;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public ToolDescriptor getToolDescriptor() {
        return toolDescriptor;
    }

    public Optional<String> getParameterByKey(String key) {
        return Optional.ofNullable(parameters.get(key));
    }

    public boolean isParameterPresent(String key) {
        return getParameterByKey(key).isPresent();
    }
}
