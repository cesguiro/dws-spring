package es.cesguiro.common.locale;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;

public class CustomLocaleChangeInterceptor extends LocaleChangeInterceptor {

    private final String defaultLanguage = "es";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException {
        String lang = request.getHeader("Accept-Language");
        //Locale locale = lang != null ? Locale.forLanguageTag(lang) : Locale.getDefault();
        Locale locale = lang != null ? Locale.forLanguageTag(lang) : Locale.of(defaultLanguage);

        LanguageUtils.setCurrentLocale(locale);

        return super.preHandle(request, response, handler);
    }
}
