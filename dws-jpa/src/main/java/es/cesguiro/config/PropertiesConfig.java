package es.cesguiro.config;

import java.io.IOException;
import java.util.Properties;

public class PropertiesConfig {

    private static final Properties properties = new Properties();

    static {
        try {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            properties.load(loader.getResourceAsStream("application.properties"));
        } catch (IOException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static String getSetting(String key) {
        return properties.getProperty(key);
    }

}
