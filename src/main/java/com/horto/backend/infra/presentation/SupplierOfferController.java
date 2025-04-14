package com.horto.backend.infra.presentation;

import com.horto.backend.core.entities.Product;
import com.horto.backend.core.entities.Supplier;
import com.horto.backend.core.entities.SupplierOffer;
import com.horto.backend.core.usecases.product.get.GetProductByIdCase;
import com.horto.backend.core.usecases.supplierOffer.delete.DeleteOfferByIdCase;
import com.horto.backend.core.usecases.supplierOffer.get.GetOfferByIdCase;
import com.horto.backend.core.usecases.supplierOffer.get.GetOffersByProductAndSupplierCase;
import com.horto.backend.core.usecases.supplierOffer.get.GetOffersByProductCase;
import com.horto.backend.core.usecases.supplierOffer.get.GetOffersBySupplierIdCase;
import com.horto.backend.core.usecases.supplierOffer.patch.PatchOfferByIdCase;
import com.horto.backend.core.usecases.supplierOffer.post.CreateOfferCase;
import com.horto.backend.core.usecases.suppliers.get.GetSupplierByIdCase;
import com.horto.backend.infra.dto.supplierOffer.request.SupplierOfferPatchDTO;
import com.horto.backend.infra.dto.supplierOffer.request.SupplierOfferRequestDTO;
import com.horto.backend.infra.dto.supplierOffer.response.SupplierOfferResponseDTO;
import com.horto.backend.infra.dto.supplierOffer.response.SupplierOffersGroupedResponseDTO;
import com.horto.backend.infra.dto.supplierOffer.response.SupplierOffersSummaryDTO;
import com.horto.backend.infra.mapper.ProductMapper;
import com.horto.backend.infra.mapper.SupplierMapper;
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

    private final DeleteOfferByIdCase deleteOfferByIdCase;

    private final PatchOfferByIdCase patchOfferByIdCase;

    private final GetProductByIdCase getProductByIdCase;
    private final GetSupplierByIdCase getSupplierByIdCase;

    private final GetOffersByProductCase getOffersByProductCase;
    private final GetOfferByIdCase getOfferByIdCase;
    private final GetOffersByProductAndSupplierCase getOffersByProductAndSupplierCase;
    private final GetOffersBySupplierIdCase getOffersBySupplierIdCase;

    private final SupplierOfferMapper supplierOfferMapper;
    private final ProductMapper productMapper;
    private final SupplierMapper supplierMapper;


    @GetMapping("/{id}")
    public ResponseEntity<SupplierOfferResponseDTO> getOfferById(@PathVariable Long id) {
        SupplierOffer supplierOffer = getOfferByIdCase.execute(id);
        return ResponseEntity.ok(supplierOfferMapper.toResponseDTO(supplierOffer));
    }

    @GetMapping("/supplier/{id}")
    public ResponseEntity<List<SupplierOfferResponseDTO>> getOffersBySupplierId(@PathVariable Long id) {
        List<SupplierOffer> offerList = getOffersBySupplierIdCase.execute(id);
        return ResponseEntity.ok(offerList.stream()
                .map(supplierOfferMapper::toResponseDTO)
                .toList()
        );
    }

    @GetMapping("/product/{productId}/supplier/{supplierId}/offers")
    public ResponseEntity<SupplierOffersGroupedResponseDTO> getOffersByProductId(@PathVariable Long productId, @PathVariable Long supplierId) {
        SupplierOffersGroupedResponseDTO offerList = getOffersByProductAndSupplierCase.execute(productId, supplierId);
        return ResponseEntity.ok(offerList);
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteOfferByIdCase.execute(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SupplierOfferResponseDTO> updateOffer(@PathVariable Long id, @Valid @RequestBody SupplierOfferPatchDTO patchDTO) {
        SupplierOffer updatedOffer = patchOfferByIdCase.execute(id, patchDTO);
        return ResponseEntity.ok(supplierOfferMapper.toResponseDTO(updatedOffer));
    }

}
