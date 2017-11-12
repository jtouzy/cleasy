package com.jtouzy.cleasy.configuration;

import org.reflections.Reflections;

import java.util.Set;

public class ConfigurationBuilder {
    public static final Configuration build() {
        Reflections annotationsFinder = new Reflections();
        Set<Class<?>> classes = annotationsFinder.getTypesAnnotatedWith(CleasyConfiguration.class);
        if (classes.isEmpty()) {
            return new DefaultConfiguration();
        } else if (classes.size() > 1) {
            throw new EnvironmentConfigurationException("Multiple @CleasyConfiguration annotations in classpath is not supported.");
        } else {
            try {
                Class<?> cleasyContextDescriptorClass = classes.iterator().next();
                return (Configuration) cleasyContextDescriptorClass.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                throw new EnvironmentConfigurationException("Cannot instanciate object from class annotated with @CleasyConfiguration", e);
            } catch (ClassCastException e) {
                throw new EnvironmentConfigurationException("@CleasyConfiguration must implements Configuration class.");
            }
        }
    }
}
