package com.horto.backend.core.exceptions.quality;

public class QualityNotFoundException extends RuntimeException {
    public QualityNotFoundException(String message) {
        super("Qualidade não encontrada com: " + message);
    }
}
