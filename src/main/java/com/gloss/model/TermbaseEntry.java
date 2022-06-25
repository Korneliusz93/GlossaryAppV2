package com.gloss.model;

import javax.persistence.*;
import java.time.LocalDate;

import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "termbase_entries")
public class TermbaseEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "term")
    private String term;

    @Column(name = "term_description")
    private String termDescription;

    @OneToOne
    private Language language;

    @OneToOne
    private User creator;

    @OneToOne
    private User updater;

    @Column(name = "entry_creation_date")
    private LocalDate entryCreationDate;

    public void setEntryCreationDate(LocalDate entryCreationDate) {
        this.entryCreationDate = LocalDate.now();
    }

    @Column(name = "entry_update_date")
    private LocalDate entryUpdateDate;

    public void setEntryUpdateDate(LocalDate entryUpdateDate) {
        this.entryUpdateDate = LocalDate.now();
    }
}
