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

    private String language;

    private User author;

    //private User updater;

    private LocalDate entryInputDate;

    //private LocalDate entryUpdateDate;
}
