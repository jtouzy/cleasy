package com.jtouzy.cleasy;

import com.jtouzy.cleasy.configuration.Configuration;
import com.jtouzy.cleasy.configuration.ConfigurationBuilder;
import com.jtouzy.cleasy.repository.ToolsRepository;
import com.jtouzy.cleasy.repository.ToolsRepositoryBuilder;

public class Cleasy {
    private static Configuration configuration;
    private static ToolsRepository repository;

    public static final Configuration getConfiguration() {
        if (configuration == null) {
            configuration = ConfigurationBuilder.build();
        }
        return configuration;
    }

    public static final ToolsRepository getToolsRepository() {
        if (repository == null) {
            repository = ToolsRepositoryBuilder.build();
        }
        return repository;
    }
}
