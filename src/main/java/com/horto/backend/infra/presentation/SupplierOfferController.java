package com.horto.backend.infra.presentation;

import com.horto.backend.core.entities.SupplierOffer;
import com.horto.backend.core.usecases.supplierOffer.get.GetOffersByProductCase;
import com.horto.backend.core.usecases.supplierOffer.get.GetOffersBySupplierIdCase;
import com.horto.backend.core.usecases.supplierOffer.post.CreateOfferCase;
import com.horto.backend.infra.dto.supplierOffer.request.SupplierOfferRequestDTO;
import com.horto.backend.infra.dto.supplierOffer.response.SupplierOfferResponseDTO;
import com.horto.backend.infra.dto.supplierOffer.response.SupplierOffersSummaryDTO;
import com.horto.backend.infra.mapper.SupplierOfferMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/supplier-offer")
@RequiredArgsConstructor
public class SupplierOfferController {

    private final CreateOfferCase createOfferCase;

    private final GetOffersByProductCase getOffersByProductCase;
    private final GetOffersBySupplierIdCase getOffersBySupplierIdCase;

    private final SupplierOfferMapper supplierOfferMapper;


    @GetMapping("/supplier/{id}")
    public ResponseEntity<List<SupplierOfferResponseDTO>> getOffersBySupplierId(@PathVariable Long id) {
        List<SupplierOffer> offerList = getOffersBySupplierIdCase.execute(id);
        return ResponseEntity.ok(offerList.stream()
                .map(supplierOfferMapper::toResponseDTO)
                .toList()
        );
    }

    @GetMapping("/product/{productId}/suppliers")
    public ResponseEntity<List<SupplierOffersSummaryDTO>> getSuppliersByProduct(@PathVariable Long productId) {
        return ResponseEntity.ok(getOffersByProductCase.execute(productId));
    }

    @PostMapping
    public ResponseEntity<SupplierOfferResponseDTO> create(@RequestBody @Valid SupplierOfferRequestDTO supplierOfferRequestDTO) {
        SupplierOffer newOffer = createOfferCase.execute(supplierOfferRequestDTO);
        return ResponseEntity.ok(supplierOfferMapper.toResponseDTO(newOffer));
    }

}
