package com.jtouzy.cleasy.tools.execution;

import com.jtouzy.cleasy.configuration.Configuration;
import com.jtouzy.cleasy.tools.metadata.descriptors.ParameterDescriptor;
import com.jtouzy.cleasy.tools.metadata.descriptors.ToolDescriptor;

import java.util.Iterator;
import java.util.Optional;

public abstract class AbstractExecutor implements Executor {
    private ExecutionContext context;

    public AbstractExecutor(ExecutionContext context) {
        this.context = context;
    }

    public ExecutionContext getContext() {
        return context;
    }

    public Configuration getConfiguration() {
        return context.getConfiguration();
    }

    public ToolDescriptor getToolDescriptor() {
        return context.getToolDescriptor();
    }

    public String getRequiredParameterByKey(String key) {
        Optional<String> parameter = context.getParameterByKey(key);
        if (parameter.isPresent()) {
            return parameter.get();
        }
        throw new ExecutionException("Required parameter [" + key + "] is not set. Does the required property is true?");
    }

    public Optional<String> getParameterByKey(String key) {
        return context.getParameterByKey(key);
    }

    public boolean isParameterPresent(String key) {
        return context.isParameterPresent(key);
    }

    @Override
    public void validateArguments(ExecutionContext context) {
        Iterator<ParameterDescriptor> it = getToolDescriptor().getParametersIterator();
        ParameterDescriptor parameterDescriptor;
        while (it.hasNext()) {
            parameterDescriptor = it.next();
            if (parameterDescriptor.isRequired() && !context.getParameterByKey(parameterDescriptor.getId()).isPresent()) {
                throw new ExecutionException("Parameter [" + parameterDescriptor.getId() + "] is required");
            }
        }
    }

    @Override
    public abstract void execute() throws Exception;
}
