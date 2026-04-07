package com.api.hex.dto;

public record BookRespDto(
        long id,
        String title,
        String author,
        String isbn,
        Integer publicationYear
) {
}
