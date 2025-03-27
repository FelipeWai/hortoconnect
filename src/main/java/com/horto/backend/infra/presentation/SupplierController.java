package com.horto.backend.infra.presentation;

import com.horto.backend.core.entities.Supplier;
import com.horto.backend.core.usecases.suppliers.delete.DeleteSupplierCase;
import com.horto.backend.core.usecases.suppliers.get.GetAllSuppliersCase;
import com.horto.backend.core.usecases.suppliers.get.GetSupplierByIdCase;
import com.horto.backend.core.usecases.suppliers.patch.PatchSupplierCase;
import com.horto.backend.core.usecases.suppliers.post.CreateSupplierCase;
import com.horto.backend.infra.dto.supplier.request.SupplierPatchDTO;
import com.horto.backend.infra.dto.supplier.request.SupplierRequestDTO;
import com.horto.backend.infra.dto.supplier.response.SupplierResponseDTO;
import com.horto.backend.infra.mapper.SupplierMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/supplier")
@RequiredArgsConstructor
public class SupplierController {

    private final DeleteSupplierCase deleteSupplierCase;

    private final PatchSupplierCase patchSupplierCase;

    private final GetAllSuppliersCase getAllSuppliersCase;
    private final GetSupplierByIdCase getSupplierByIdCase;

    private final CreateSupplierCase createSupplierCase;

    private final SupplierMapper supplierMapper;

    @GetMapping
    public ResponseEntity<List<SupplierResponseDTO>> getAllSuppliers() {
        List<Supplier> allSuppliers = getAllSuppliersCase.execute();
        return ResponseEntity.ok(allSuppliers.stream()
                .map(supplierMapper::toResponseDTO)
                .toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierResponseDTO> getSupplier(@PathVariable Long id) {
        Supplier supplier = getSupplierByIdCase.execute(id);
        return ResponseEntity.ok(supplierMapper.toResponseDTO(supplier));
    }

    @PostMapping
    public ResponseEntity<SupplierResponseDTO> createSupplier(@RequestBody @Valid SupplierRequestDTO supplierRequestDTO) {
        Supplier supplier = createSupplierCase.execute(supplierRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(supplierMapper.toResponseDTO(supplier));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable Long id) {
        deleteSupplierCase.execute(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<SupplierResponseDTO> patchSupplier(@PathVariable Long id, @RequestBody @Valid SupplierPatchDTO patchDTO) {
        Supplier supplier = patchSupplierCase.execute(id, patchDTO);
        return ResponseEntity.status(HttpStatus.OK).body(supplierMapper.toResponseDTO(supplier));
    }

}
