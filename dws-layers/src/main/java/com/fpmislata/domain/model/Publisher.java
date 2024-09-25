package com.fpmislata.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
//@NoArgsConstructor
@RequiredArgsConstructor
public class Publisher {

    private long id;
    private String name;
    private String slug;
}
