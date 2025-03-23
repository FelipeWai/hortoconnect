package com.horto.backend.core.usecases.productPicture.post;

import com.horto.backend.core.entities.ProductPicture;
import com.horto.backend.core.gateway.ProductPictureGateway;

import java.util.List;

public class CreateProductPicturesCaseImpl implements CreateProductPicturesCase {

    private final ProductPictureGateway productPictureGateway;

    public CreateProductPicturesCaseImpl(final ProductPictureGateway productPictureGateway) {
        this.productPictureGateway = productPictureGateway;
    }

    @Override
    public List<ProductPicture> execute(List<ProductPicture> productPictures) {
        return productPictureGateway.createProductPictures(productPictures);
    }
}
