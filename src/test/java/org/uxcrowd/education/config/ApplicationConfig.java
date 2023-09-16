package org.uxcrowd.education.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApplicationConfig {
    public final String env;

    public final String baseUrl;

    public final String apiUrl;

    public final String testerUsername;

    public final String testerPassword;

    public final String clientUsername;

    public final String clientPass;

    public final String clientPageURL;

    public final String testerPageURL;

    private static final String ENV_NAME = "ACTIVE_ENVIRONMENT";
    public ApplicationConfig() {
        String getenv = System.getenv(ENV_NAME);
        if(getenv == null) env = "default";
        else env = getenv;
        Properties properties = parseProperties();
        this.baseUrl = properties.getProperty("base.url");
        this.apiUrl = properties.getProperty("api.url");
        this.clientUsername = properties.getProperty("client.login");
        this.clientPass = properties.getProperty("client.password");
        this.testerUsername = properties.getProperty("tester.login");
        this.testerPassword = properties.getProperty("tester.password");
        this.clientPageURL = properties.getProperty("client.url");
        this.testerPageURL = properties.getProperty("tester.url");
    }

    private Properties parseProperties() {
        String filename = env + ".properties";
        try(
            InputStream inputStream = getClass().getClassLoader().getResource(filename)
            .openStream();
        ) {
            Properties properties = new Properties();
            properties.load(inputStream);
            return properties;
        } catch (IOException e){
            throw new RuntimeException();
        }
    }
}
