package com.hexaware.careercrafter.dto;

import java.util.List;

public record JobListingPageRespDto(
        List<JobListingRespDto> data,
        long totalRecords,
        int totalPages
) {
}
