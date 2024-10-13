package es.cesguiro.common.locale;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;

public class CustomLocaleChangeInterceptor extends LocaleChangeInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException {
        String lang = request.getHeader("Accept-Language");
        Locale locale = lang != null ? Locale.forLanguageTag(lang) : Locale.getDefault();

        LanguageUtils.setCurrentLocale(locale);

        return super.preHandle(request, response, handler);
    }
}
