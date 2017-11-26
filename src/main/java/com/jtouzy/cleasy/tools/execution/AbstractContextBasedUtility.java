package com.jtouzy.cleasy.tools.execution;

public abstract class AbstractContextBasedUtility {
    private ExecutionContext context;

    public AbstractContextBasedUtility(ExecutionContext context) {
        this.context = context;
    }

    public ExecutionContext getContext() {
        return context;
    }
}

