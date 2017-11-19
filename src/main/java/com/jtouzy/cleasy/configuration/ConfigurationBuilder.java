package com.jtouzy.cleasy.configuration;

import org.reflections.Reflections;

import java.util.Set;

public class ConfigurationBuilder {
    private static final Configuration defaultConfiguration = new DefaultConfiguration();
    private static String scanPackage;

    public static final void setScanPackage(String scanPackage) {
        ConfigurationBuilder.scanPackage = scanPackage;
    }

    public static final Configuration getDefaultConfiguration() {
        return defaultConfiguration;
    }

    public static final Configuration build() {
        Reflections annotationsFinder = new Reflections(scanPackage);
        Set<Class<?>> classes = annotationsFinder.getTypesAnnotatedWith(CleasyConfiguration.class);
        if (classes.isEmpty()) {
            return defaultConfiguration;
        } else if (classes.size() > 1) {
            throw new ConfigurationException("Multiple @CleasyConfiguration annotations in classpath is not supported.");
        } else {
            try {
                Class<?> cleasyContextDescriptorClass = classes.iterator().next();
                return (Configuration) cleasyContextDescriptorClass.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                throw new ConfigurationException("Cannot instantiate object from class annotated with @CleasyConfiguration", e);
            } catch (ClassCastException e) {
                throw new ConfigurationException("@CleasyConfiguration must implements com.jtouzy.cleasy.Configuration class.");
            }
        }
    }
}
