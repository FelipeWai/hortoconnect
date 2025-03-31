package com.horto.backend.core.exceptions.supplierOffer;

public class OffersNotFoundException extends RuntimeException {
    public OffersNotFoundException(String message) {
        super("Ofertas não encontradas para o produto: " + message);
    }
}
