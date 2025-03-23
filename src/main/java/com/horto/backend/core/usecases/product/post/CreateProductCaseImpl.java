package com.horto.backend.core.usecases.product.post;

import com.horto.backend.core.entities.Product;
import com.horto.backend.core.gateway.ProductGateway;
import com.horto.backend.infra.dto.product.request.ProductRequestDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class CreateProductCaseImpl implements CreateProductCase {
    private final ProductGateway productGateway;

    public CreateProductCaseImpl(ProductGateway productGateway) {
        this.productGateway = productGateway;
    }

    @Override
    public Product execute(ProductRequestDTO requestDTO, List<MultipartFile> pictures) {
        return productGateway.createProduct(requestDTO, pictures);
    }
}
