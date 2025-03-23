package com.horto.backend.core.usecases.product.post;

import com.horto.backend.core.entities.Product;
import com.horto.backend.infra.dto.product.request.ProductRequestDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CreateProductCase {
    Product execute(ProductRequestDTO requestDTO, List<MultipartFile> pictures);
}
