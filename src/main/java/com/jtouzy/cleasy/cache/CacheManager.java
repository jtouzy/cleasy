package com.jtouzy.cleasy.cache;

import com.jtouzy.cleasy.configuration.Configuration;
import com.jtouzy.cleasy.configuration.ConfigurationBuilder;
import com.jtouzy.cleasy.tools.metadata.descriptors.ToolDescriptor;
import com.jtouzy.cleasy.tools.metadata.descriptors.ToolDescriptorsBuilder;

import java.util.List;

public class CacheManager {
    private static Configuration configuration;
    private static List<ToolDescriptor> repository;

    public static final Configuration getConfiguration() {
        if (configuration == null) {
            configuration = ConfigurationBuilder.build();
        }
        return configuration;
    }

    public static final List<ToolDescriptor> getToolsRepository() {
        if (repository == null) {
            repository = ToolDescriptorsBuilder.build();
        }
        return repository;
    }
}
