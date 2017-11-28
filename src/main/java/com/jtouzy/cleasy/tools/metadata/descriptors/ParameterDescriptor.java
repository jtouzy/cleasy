package com.jtouzy.cleasy.tools.metadata.descriptors;

public class ParameterDescriptor {
    private String id;
    private String shortId;
    private String description;
    private boolean required;

    public ParameterDescriptor(String id, String shortId, String description, boolean required) {
        this.id = id;
        this.shortId = shortId;
        this.description = description;
        this.required = required;
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

    public boolean isRequired() {
        return required;
    }
}
