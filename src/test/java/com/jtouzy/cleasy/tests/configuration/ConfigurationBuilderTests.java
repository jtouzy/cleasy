package com.jtouzy.cleasy.tests.configuration;

import com.jtouzy.cleasy.configuration.Configuration;
import com.jtouzy.cleasy.configuration.ConfigurationBuilder;
import com.jtouzy.cleasy.configuration.ConfigurationException;
import com.jtouzy.cleasy.tests.configuration.classes.simpleConfiguration.SimpleConfiguration;
import org.junit.Assert;
import org.junit.Test;

public class ConfigurationBuilderTests {
    private static final String testClasses = "com.jtouzy.cleasy.tests.configuration.classes";

    public void scanAndExpectException(String scanPackage, String exceptionMessage) {
        //ConfigurationBuilder.setScanPackage(scanPackage);
        try {
            ConfigurationBuilder.build();
            Assert.fail("ConfigurationException should be thrown");
        } catch (ConfigurationException ex) {
            Assert.assertEquals(exceptionMessage, ex.getMessage());
        }
    }

    @Test
    public void testWithNoConfigurationClassProvided() {
        //ConfigurationBuilder.setScanPackage(testClasses + ".noConfigurationClassPackage");
        Configuration configuration = ConfigurationBuilder.build();
        Assert.assertEquals(ConfigurationBuilder.getDefaultConfiguration(), configuration);
    }

    @Test
    public void testWithMultipleConfigurationClassProvided() {
        scanAndExpectException(testClasses + ".multipleConfigurationClasses",
                "Multiple @CleasyConfiguration annotations in classpath is not supported.");
    }

    @Test
    public void testWithWrongConfigurationClass() {
        scanAndExpectException(testClasses + ".wrongConfigurationClass",
                "@CleasyConfiguration must implements com.jtouzy.cleasy.Configuration class.");
    }

    @Test
    public void testWithSimpleConfiguration() {
        //ConfigurationBuilder.setScanPackage(testClasses + ".simpleConfiguration");
        Configuration configuration = ConfigurationBuilder.build();
        Assert.assertEquals(SimpleConfiguration.class, configuration.getClass());
    }
}
