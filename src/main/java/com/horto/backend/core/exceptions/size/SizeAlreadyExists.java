package com.horto.backend.core.exceptions.size;

public class SizeAlreadyExists extends RuntimeException {
    public SizeAlreadyExists(String message) {
        super("Tamanho com nome " + message + " já existe");
    }
}
