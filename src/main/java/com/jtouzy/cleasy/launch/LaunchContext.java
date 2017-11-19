package com.jtouzy.cleasy.launch;

import com.jtouzy.cleasy.configuration.Configuration;
import com.jtouzy.cleasy.tools.metadata.descriptors.ToolDescriptor;

import java.util.List;

public class LaunchContext {
    private Configuration configuration;
    private List<ToolDescriptor> repository;

    public LaunchContext(Configuration configuration, List<ToolDescriptor> repository) {
        this.configuration = configuration;
        this.repository = repository;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public List<ToolDescriptor> getRepository() {
        return repository;
    }
}
