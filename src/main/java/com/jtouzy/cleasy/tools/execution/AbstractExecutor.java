package com.jtouzy.cleasy.tools.execution;

import com.jtouzy.cleasy.configuration.Configuration;
import com.jtouzy.cleasy.tools.metadata.descriptors.ToolDescriptor;

import java.util.Optional;

public abstract class AbstractExecutor implements Executor {
    private ExecutionContext context;

    public AbstractExecutor(ExecutionContext context) {
        this.context = context;
    }

    public Configuration getConfiguration() {
        return context.getConfiguration();
    }

    public ToolDescriptor getToolDescriptor() {
        return context.getToolDescriptor();
    }

    public Optional<String> getParameterByKey(String key) {
        return context.getParameterByKey(key);
    }

    public boolean isParameterPresent(String key) {
        return context.isParameterPresent(key);
    }

    @Override
    public abstract void execute();
}
