package com.horto.backend.core.gateway;

import com.horto.backend.core.entities.SupplierOffer;
import com.horto.backend.infra.dto.supplier.request.SupplierPatchDTO;
import com.horto.backend.infra.dto.supplierOffer.request.SupplierOfferPatchDTO;
import com.horto.backend.infra.dto.supplierOffer.request.SupplierOfferRequestDTO;
import com.horto.backend.infra.dto.supplierOffer.response.SupplierOfferResponseDTO;
import com.horto.backend.infra.dto.supplierOffer.response.SupplierOffersGroupedResponseDTO;
import com.horto.backend.infra.dto.supplierOffer.response.SupplierOffersSummaryDTO;
import com.horto.backend.infra.filters.offer.OfferFilter;

import java.util.List;
import java.util.Optional;

public interface SupplierOfferGateway {

    List<SupplierOffersGroupedResponseDTO<SupplierOfferResponseDTO>> getOffersBySupplierId(Long id);

    SupplierOffer createOffer(SupplierOfferRequestDTO requestDTO);

    Optional<SupplierOffer> getOfferById(Long id);

    SupplierOffer patchOfferById(Long id, SupplierOfferPatchDTO patchDTO);

    SupplierOffersGroupedResponseDTO getOffersByProductAndSupplierId(Long productId, Long supplierId);

    List<SupplierOffersSummaryDTO> getOffersByProductId(Long productId, OfferFilter filter);

    void deleteOfferById(Long id);

}
