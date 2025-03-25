package com.horto.backend.core.usecases.productPicture.get;

import com.horto.backend.core.entities.ProductPicture;
import com.horto.backend.core.gateway.ProductPictureGateway;

import java.util.List;

public class GetProductPicturesByProductIdCaseImpl implements GetProductPicturesByProductIdCase {

    private final ProductPictureGateway productPictureGateway;

    public GetProductPicturesByProductIdCaseImpl(final ProductPictureGateway productPictureGateway) {
        this.productPictureGateway = productPictureGateway;
    }

    @Override
    public List<ProductPicture> execute(Long productId) {
        return productPictureGateway.getProductPictureByProductId(productId);
    }
}
