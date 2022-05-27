package com.gloss.model;

import javax.persistence.*;

import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "languages")
public class Language {

    @Id
    @Column(name = "language_code")
    private String languageCode;



}
