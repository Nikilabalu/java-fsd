package com.api.hex.dto;

import java.util.List;

public record BookPageRespDto(
        List<BookRespDto> data,
        int currentPage,
        int totalPages,
        long totalElements
) {
}
