package com.hexaware.careercrafter.dto;

import java.util.List;

public record PageRespDto(
        List<?> data,
        long totalRecords,
        int totalPages
) {
}