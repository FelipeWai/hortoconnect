package com.horto.backend.core.usecases.productPicture.get;

import com.horto.backend.core.entities.ProductPicture;

import java.util.List;

public interface GetProductPicturesByProductIdCase {

    List<ProductPicture> execute(Long productId);

}
