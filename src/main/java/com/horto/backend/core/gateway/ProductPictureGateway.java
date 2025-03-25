package com.horto.backend.core.gateway;

import com.horto.backend.core.entities.ProductPicture;

import java.util.List;

public interface ProductPictureGateway {

    List<ProductPicture> createProductPictures(List<ProductPicture> productPictures);

    List<ProductPicture> getProductPictureByProductId(Long productId);

}
