package com.horto.backend.core.exceptions.supplierOffer;

public class OneOfferNotFoundException extends RuntimeException {
    public OneOfferNotFoundException(String message) {
        super("Oferta única não encontrada com: " + message);
    }
}
