package com.jtouzy.cleasy.configuration;

import org.atteo.classindex.ClassIndex;

import java.util.Iterator;

public class ConfigurationBuilder {
    private static final Configuration defaultConfiguration = new DefaultConfiguration();

    public static final Configuration getDefaultConfiguration() {
        return defaultConfiguration;
    }

    public static final Configuration build() {
        Iterable<Class<?>> classes = ClassIndex.getAnnotated(CleasyConfiguration.class);
        if (!classes.iterator().hasNext()) {
            return defaultConfiguration;
        } else {
            Iterator<Class<?>> it = classes.iterator();
            Class<?> cleasyContextDescriptorClass = null;
            if (it.hasNext()) {
                cleasyContextDescriptorClass = it.next();
            }
            if (it.hasNext()) {
                throw new ConfigurationException("Multiple @CleasyConfiguration annotations in classpath is not supported.");
            }
            if (cleasyContextDescriptorClass != null) {
                try {
                    return (Configuration) cleasyContextDescriptorClass.newInstance();
                } catch (InstantiationException | IllegalAccessException e) {
                    throw new ConfigurationException("Cannot instantiate object from class annotated with @CleasyConfiguration", e);
                } catch (ClassCastException e) {
                    throw new ConfigurationException("@CleasyConfiguration must implements com.jtouzy.cleasy.Configuration class.");
                }
            }
            return defaultConfiguration;
        }
    }
}
