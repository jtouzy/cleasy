package com.jtouzy.cleasy.cli;

import java.util.Map;

public class CommandDescription {
    private String toolName;
    private Map<String, String> parameters;

    public CommandDescription(String toolName, Map<String, String> parameters) {
        this.toolName = toolName;
        this.parameters = parameters;
    }

    public String getToolName() {
        return toolName;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }
}
