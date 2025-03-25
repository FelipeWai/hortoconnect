package com.horto.backend.core.exceptions.productPicture;

public class ProductPictureNotFoundException extends RuntimeException {
    public ProductPictureNotFoundException(String message) {
        super("Product picture not found: " + message);
    }
}
