package com.horto.backend.core.exceptions.quality;

public class QualityAlreadyExists extends RuntimeException {
    public QualityAlreadyExists(String message) {
        super("Qualidade " + message + " já existe");
    }
}
