package es.cesguiro.config;

import java.io.IOException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PropertiesConfig {

    private static final Properties properties = new Properties();
    private static final Pattern PLACEHOLDER_PATTERN = Pattern.compile("\\$\\{(.+?)}");

    static {
        try {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            properties.load(loader.getResourceAsStream("application.properties"));
            resolveAllPlaceholders();
        } catch (IOException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static String getSetting(String key) {
        return properties.getProperty(key);
    }

    private static void resolveAllPlaceholders() {
        properties.forEach((key, value) -> {
            if (value instanceof String) {
                properties.setProperty((String) key, resolvePlaceholders((String) value));
            }
        });
    }

    private static String resolvePlaceholders(String value) {
        Matcher matcher = PLACEHOLDER_PATTERN.matcher(value);
        StringBuilder resolvedValue = new StringBuilder();

        while (matcher.find()) {
            String placeholder = matcher.group(1); // Captura el contenido entre ${}
            String resolved = properties.getProperty(placeholder);

            if (resolved == null) {
                throw new IllegalArgumentException("No se pudo resolver la propiedad: " + placeholder);
            }

            matcher.appendReplacement(resolvedValue, resolved);
        }

        matcher.appendTail(resolvedValue);
        return resolvedValue.toString();
    }

}
