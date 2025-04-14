package com.horto.backend.infra.dto.supplierOffer.response;

import com.horto.backend.infra.dto.product.response.ProductNameResponseDTO;
import com.horto.backend.infra.dto.supplier.response.SupplierResponseDTO;

import java.util.List;

public record SupplierOffersGroupedResponseDTO<T>(
        ProductNameResponseDTO product,
        SupplierResponseDTO supplier,
        List<T> offers
) { }
