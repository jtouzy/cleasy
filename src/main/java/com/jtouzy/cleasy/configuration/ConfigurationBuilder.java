package com.jtouzy.cleasy.configuration;

import com.jtouzy.cleasy.utils.ClassUtils;

import java.util.Iterator;

public class ConfigurationBuilder {
    private static String scanPackage;
    private static final Configuration defaultConfiguration = new DefaultConfiguration();

    public static final void setScanPackage(String scanPackage) {
        ConfigurationBuilder.scanPackage = scanPackage;
    }

    public static final Configuration getDefaultConfiguration() {
        return defaultConfiguration;
    }

    public static final Configuration build() {
        Iterable<Class<?>> classes = ClassUtils.getClassesAnnotatedWith(CleasyConfiguration.class, scanPackage);
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
