package com.gloss.model;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class LanguageDto {

    @NotEmpty(message = "Language Code must be not empty")
    private String languageCode;
}
