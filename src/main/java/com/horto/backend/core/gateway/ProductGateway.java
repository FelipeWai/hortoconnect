package com.horto.backend.core.gateway;

import com.horto.backend.core.entities.Product;
import com.horto.backend.infra.dto.product.request.ProductPatchDTO;
import com.horto.backend.infra.dto.product.request.ProductRequestDTO;
import com.horto.backend.infra.filters.product.ProductFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface ProductGateway {

    Page<Product> getAllProducts(ProductFilter filter, Pageable pageable);

    Optional<Product> getProductById(Long id);

    Optional<Product> getProductByName(String name);

    Product createProduct(ProductRequestDTO requestDTO, List<MultipartFile> pictures);

    void deleteProductById(Long id);

    Product patchProductById(Long id, ProductPatchDTO patchDTO);

}
