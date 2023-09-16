package org.uxcrowd.education.cucumber;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.core.options.Constants.*;
import static io.cucumber.core.options.Constants.PLUGIN_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("/")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "org.uxcrowd.education.cucumber.stepdefs")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "org.uxcrowd.education.cucumber")
@ConfigurationParameter(key = FEATURES_PROPERTY_NAME, value = "classpath:features")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm")
public class CucumberRunner {
}
