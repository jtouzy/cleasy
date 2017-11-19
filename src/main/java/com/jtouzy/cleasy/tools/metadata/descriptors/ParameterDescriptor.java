package com.jtouzy.cleasy.tools.metadata.descriptors;

public class ParameterDescriptor {
    private String id;
    private String shortId;
    private String description;

    public ParameterDescriptor(String id, String shortId, String description) {
        this.id = id;
        this.shortId = shortId;
        this.description = description;
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
}
