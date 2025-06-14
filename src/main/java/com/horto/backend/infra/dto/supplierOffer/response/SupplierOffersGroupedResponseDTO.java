package com.horto.backend.infra.dto.supplierOffer.response;

import com.horto.backend.infra.dto.product.response.ProductNameResponseDTO;

import java.util.List;

public record SupplierOffersGroupedResponseDTO<T>(
        ProductNameResponseDTO product,
        List<T> offers
) { }
