package com.gloss.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class TermbaseEntryDto {

    private int id;

    private String term;

    private String termDescription;

    private Language language;

    private User creator;

    private User updater;

    private LocalDate entryCreationDate;

    private LocalDate entryUpdateDate;
}
