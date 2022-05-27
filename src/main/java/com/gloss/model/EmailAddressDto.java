package com.gloss.model;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class EmailAddressDto {

    private int id;

    @NotEmpty(message = "Email address name must be not empty")
    private String emailAddress;
}
