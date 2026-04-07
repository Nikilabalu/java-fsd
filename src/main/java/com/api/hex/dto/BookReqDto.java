package com.api.hex.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BookReqDto(
        @NotBlank(message = "title cannot be blank")
        String title,

        @NotBlank(message = "author cannot be blank")
        String author,

        @NotBlank(message = "isbn cannot be blank")
        String isbn,

        @NotNull(message = "publicationYear cannot be null")
        Integer publicationYear
) {
}
