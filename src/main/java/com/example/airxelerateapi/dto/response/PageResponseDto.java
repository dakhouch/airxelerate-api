package com.example.airxelerateapi.dto.response;
import java.util.List;

public record PageResponseDto<T>(Pagination pagination,List<T> page) {}
