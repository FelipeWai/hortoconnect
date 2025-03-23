package com.horto.backend.core.usecases.productPicture.post;

import com.horto.backend.core.entities.ProductPicture;

import java.util.List;

public interface CreateProductPicturesCase {

    List<ProductPicture> execute(List<ProductPicture> productPictures);
}
