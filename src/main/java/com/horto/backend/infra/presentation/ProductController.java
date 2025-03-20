package com.horto.backend.infra.presentation;

import com.horto.backend.core.entities.Product;
import com.horto.backend.core.usecases.product.delete.DeleteProductByIdCase;
import com.horto.backend.core.usecases.product.get.GetAllProductsCase;
import com.horto.backend.core.usecases.product.get.GetProductByIdCase;
import com.horto.backend.core.usecases.product.patch.PatchProductByIdCase;
import com.horto.backend.core.usecases.product.post.CreateProductCase;
import com.horto.backend.infra.dto.product.request.ProductPatchDTO;
import com.horto.backend.infra.dto.product.request.ProductRequestDTO;
import com.horto.backend.infra.dto.product.response.ProductResponseDTO;
import com.horto.backend.infra.mapper.ProductMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final PatchProductByIdCase patchProductByIdCase;

    private final DeleteProductByIdCase deleteProductByIdCase;

    private final GetAllProductsCase getAllProductsCase;
    private final GetProductByIdCase getProductByIdCase;

    private final CreateProductCase createProductCase;

    private final ProductMapper productMapper;


    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        List<Product> products = getAllProductsCase.execute();
        return ResponseEntity.ok(products.stream()
                .map(productMapper::toResponseDTO)
                .toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable Long id) {
        Product product = getProductByIdCase.execute(id);
        return ResponseEntity.ok(productMapper.toResponseDTO(product));
    }

    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody @Valid ProductRequestDTO requestDTO) {
        Product newProduct = createProductCase.execute(requestDTO);
        return ResponseEntity.ok(productMapper.toResponseDTO(newProduct));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        deleteProductByIdCase.execute(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> patchProductById(@PathVariable Long id, @RequestBody ProductPatchDTO requestDTO) {
        Product product = patchProductByIdCase.execute(id, requestDTO);
        return ResponseEntity.ok(productMapper.toResponseDTO(product));
    }

}
