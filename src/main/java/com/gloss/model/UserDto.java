package com.gloss.model;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserDto {

    private int id;

    @NotEmpty(message = "Username must not be empty")
    private String username;

    @NotEmpty(message = "password must not be empty")
    private String password;

    @NotBlank(message = "First name must be not be blank")
    private String firstName;

    @NotBlank(message = "Last name must be not blank")
    private String lastName;
}
