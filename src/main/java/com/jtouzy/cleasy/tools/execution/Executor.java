package com.jtouzy.cleasy.tools.execution;

public interface Executor {
    void validateArguments(ExecutionContext context);
    void execute();
}
