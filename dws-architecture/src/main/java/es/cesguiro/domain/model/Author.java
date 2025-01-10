package es.cesguiro.domain.model;

import es.cesguiro.common.locale.LanguageUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author {

    private Long id;
    private String name;
    private String nationality;
    private String biographyEs;
    private String biographyEn;
    private int birthYear;
    private int deathYear;

    public String getBiography(){
        String language = LanguageUtils.getCurrentLanguage();
        if ("en".equals(language)) {
            return biographyEn;
        }
        return biographyEs;
    }
}
