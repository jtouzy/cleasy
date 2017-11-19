package com.jtouzy.cleasy.tools.metadata.descriptors;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ToolDescriptor {
    private String id;
    private String shortId;
    private String description;
    private List<ParameterDescriptor> parameters;

    public ToolDescriptor(String id, String shortId, String description) {
        this.id = id;
        this.shortId = shortId;
        this.description = description;
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

    public Iterator<ParameterDescriptor> getParametersIterator() {
        checkParameterList();
        return parameters.iterator();
    }
}
