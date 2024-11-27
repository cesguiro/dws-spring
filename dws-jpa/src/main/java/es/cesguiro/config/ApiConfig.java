package es.cesguiro.config;

public class ApiConfig {

    public static String getBaseUrl() {
        return PropertiesConfig.getSetting("app.base.url") + getApiPath();
    }

    private static String getApiPath() {
        return PropertiesConfig.getSetting("app.api.path");
    }

    public static String getAdminPath() {
        return PropertiesConfig.getSetting("app.admin.path");
    }

    public static String getAdminUrl() {
        return PropertiesConfig.getSetting("app.base.url") + getAdminPath();
    }
}
