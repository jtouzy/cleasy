package com.jtouzy.cleasy;

import com.jtouzy.cleasy.configuration.Configuration;
import com.jtouzy.cleasy.configuration.ConfigurationBuilder;
import com.jtouzy.cleasy.tools.metadata.descriptors.ToolDescriptor;
import com.jtouzy.cleasy.tools.metadata.descriptors.ToolDescriptorsBuilder;

import java.util.List;
import java.util.Optional;

public class CleasyContext {
    private static Configuration configuration;
    private static List<ToolDescriptor> repository;
    private static boolean initialized = false;

    public static final void initialize() {
        if (!initialized) {
            initializeConfiguration();
            initializeToolsRepository();
            initialized = true;
        }
    }

    public static final void initializeWith(Configuration customConfiguration) {
        if (!initialized) {
            initializeConfigurationWith(customConfiguration);
            initializeToolsRepository();
            initialized = true;
        }
    }

    public static final void initializeConfiguration() {
        configuration = ConfigurationBuilder.build();
    }

    public static final void initializeConfigurationWith(Configuration customConfiguration) {
        checkNotNull(customConfiguration);
        configuration = customConfiguration;
    }

    public static final Configuration getConfiguration() {
        checkConfiguration();
        return configuration;
    }

    public static final void initializeToolsRepository() {
        repository = ToolDescriptorsBuilder.build();
    }

    public static final Optional<ToolDescriptor> findToolByIdentifier(String id) {
        checkNotNull(id);
        checkToolsRepository();
        return repository.stream().filter(tool -> id.equals(tool.getId()) || id.equals(tool.getShortId())).findFirst();
    }

    private static final void checkNotNull(Object value) {
        if (value == null) {
            throw new CleasyContextException("Function parameter cannot be null.");
        }
    }

    private static final void checkConfiguration() {
        if (configuration == null) {
            throw new CleasyContextException("Configuration is not initialized.");
        }
    }

    private static final void checkToolsRepository() {
        if (repository == null) {
            throw new CleasyContextException("ToolsRepository is not initialized.");
        }
    }
}
