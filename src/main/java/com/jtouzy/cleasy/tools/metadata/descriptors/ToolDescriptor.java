package com.jtouzy.cleasy.tools.metadata.descriptors;

import com.jtouzy.cleasy.tools.execution.Executor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ToolDescriptor {
    private String id;
    private String shortId;
    private String description;
    private List<ParameterDescriptor> parameters;
    private Class<? extends Executor> executorClass;

    public ToolDescriptor(String id, String shortId, String description, Class<? extends Executor> executorClass) {
        this.id = id;
        this.shortId = shortId;
        this.description = description;
        this.executorClass = executorClass;
    }

    private void checkParameterList() {
        if (parameters == null) {
            parameters = new ArrayList<>();
        }
    }

    public void addParameter(ParameterDescriptor parameterDescriptor) {
        checkParameterList();
        parameters.add(parameterDescriptor);
    }

    public String getId() {
        return id;
    }

    public String getShortId() {
        return shortId;
    }

    public String getDescription() {
        return description;
    }

    public int getParametersCount() {
        checkParameterList();
        return parameters.size();
    }

    public List<String> getParameterIdentifiers() {
        checkParameterList();
        List<String> identifiers = parameters.stream().map(ParameterDescriptor::getId).collect(Collectors.toList());
        identifiers.addAll(parameters.stream().map(ParameterDescriptor::getShortId).collect(Collectors.toList()));
        return identifiers;
    }

    public Class<? extends Executor> getExecutorClass() {
        return executorClass;
    }
}
