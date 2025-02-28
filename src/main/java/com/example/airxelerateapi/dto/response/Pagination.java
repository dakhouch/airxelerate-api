package com.example.airxelerateapi.dto.response;

import lombok.Builder;

@Builder
public record Pagination (
         Integer currentPage,
         Integer totalPages,
         Long totalItems
){}
