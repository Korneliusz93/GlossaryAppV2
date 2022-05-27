package com.gloss.model;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CategoryDto {

    private int id;

    @NotBlank(message = "Category name must be not blank")
    private String categoryName;
}
