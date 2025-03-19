package com.horto.backend.core.exceptions.size;

public class SizeNotFoundException extends RuntimeException {
    public SizeNotFoundException(String message) {
       super("Tamanho não encontrado com: " + message);
    }
}
