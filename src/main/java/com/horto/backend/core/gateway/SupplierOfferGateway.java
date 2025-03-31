package com.horto.backend.core.gateway;

import com.horto.backend.core.entities.SupplierOffer;
import com.horto.backend.infra.dto.supplierOffer.request.SupplierOfferRequestDTO;
import com.horto.backend.infra.dto.supplierOffer.response.SupplierOffersSummaryDTO;

import java.util.List;

public interface SupplierOfferGateway {

    List<SupplierOffer> getOffersBySupplierId(Long id);

    SupplierOffer createOffer(SupplierOfferRequestDTO requestDTO);

    List<SupplierOffersSummaryDTO> getOffersByProductId(Long productId);

}
