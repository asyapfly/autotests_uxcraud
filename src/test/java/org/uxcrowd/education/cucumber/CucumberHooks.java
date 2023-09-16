package org.uxcrowd.education.cucumber;

import com.codeborne.selenide.Configuration;
import io.cucumber.java.Before;
import org.uxcrowd.education.config.ApplicationConfig;

public class CucumberHooks {

    @Before
    public void setUp() {
        ApplicationConfig config = new ApplicationConfig();
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        Configuration.browser = "chrome";
        Configuration.timeout = 10_000;
        Configuration.baseUrl = config.baseUrl;
        Configuration.holdBrowserOpen = false;
    }
}
