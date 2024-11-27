package es.cesguiro.controller;

import es.cesguiro.config.ApiConfig;
import es.cesguiro.config.PropertiesConfig;

public abstract class BasicController {

    private final String DEFAULT_PAGE_SIZE = PropertiesConfig.getSetting("app.pageSize.default");
    private final String BASE_URL = ApiConfig.getBaseUrl() + getResourcePath();
    private final String ADMIN_BASE_URL = ApiConfig.getAdminUrl() + getResourcePath();

    public abstract String getResourcePath();

    public String getDefaultPageSize() {
        return DEFAULT_PAGE_SIZE;
    };

    public String getBaseUrl() {
        return BASE_URL;
    }

    public String getAdminBaseUrl() {
        return ADMIN_BASE_URL;
    }

}
