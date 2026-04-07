package com.api.hex.dto;

public record BookFilterReqDto(
        String title,
        String author,
        Integer publicationYear
) {
}
