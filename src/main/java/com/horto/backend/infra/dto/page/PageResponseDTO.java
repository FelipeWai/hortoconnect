package com.horto.backend.infra.dto.page;

import java.util.List;

public record PageResponseDTO<T> (
        List<T> content,
        int currentPage,
        long totalItems,
        int totalPages
){
}
