package com.horto.backend.infra.presentation;

import com.horto.backend.core.entities.Product;
import com.horto.backend.core.usecases.product.delete.DeleteProductByIdCase;
import com.horto.backend.core.usecases.product.get.GetAllProductsCase;
import com.horto.backend.core.usecases.product.get.GetProductByIdCase;
import com.horto.backend.core.usecases.product.patch.PatchProductByIdCase;
import com.horto.backend.core.usecases.product.post.CreateProductCase;
import com.horto.backend.infra.dto.page.PageResponseDTO;
import com.horto.backend.infra.dto.product.request.ProductPatchDTO;
import com.horto.backend.infra.dto.product.request.ProductRequestDTO;
import com.horto.backend.infra.dto.product.response.ProductResponseDTO;
import com.horto.backend.infra.filters.product.ProductFilter;
import com.horto.backend.infra.mapper.ProductMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public ResponseEntity<PageResponseDTO<ProductResponseDTO>> getAllProducts(
            @RequestParam(required = false) Long category_id,
            @RequestParam(required = false) Long subcategory_id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection
    ) {
        Sort sort = Sort.by(sortDirection.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        ProductFilter productFilter = new ProductFilter();
        productFilter.setCategory_id(category_id);
        productFilter.setSubcategory_id(subcategory_id);
        productFilter.setName(name);

        Page<Product> productPage = getAllProductsCase.execute(productFilter, pageable);

        List<ProductResponseDTO> productResponseDTOList = productPage.stream()
                .map(productMapper::toResponseDTO)
                .toList();

        PageResponseDTO<ProductResponseDTO> response = new PageResponseDTO<>(
                productResponseDTOList,
                productPage.getNumber(),
                productPage.getTotalElements(),
                productPage.getTotalPages()
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable Long id) {
        Product product = getProductByIdCase.execute(id);
        return ResponseEntity.ok(productMapper.toResponseDTO(product));
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ProductResponseDTO> createProduct(
            @RequestPart("data") @Valid ProductRequestDTO requestDTO,
            @RequestPart(value = "pictures", required = false) List<MultipartFile> pictures) {

        Product newProduct = createProductCase.execute(requestDTO, pictures);

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
