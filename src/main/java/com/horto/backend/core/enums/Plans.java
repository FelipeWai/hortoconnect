package com.horto.backend.core.enums;

public enum Plans {
    BASICO(1L, 1.0),
    PREMIUM(2L, 350.00),
    VIP(3L, 20.0);

    private final Long id;
    private final Double valor;

    Plans(Long id, Double valor) {
        this.id = id;
        this.valor = valor;
    }

    public static Double getValorPorId(Long id) {
        for (Plans plano : values()) {
            if (plano.id.equals(id)) {
                return plano.valor;
            }
        }
        throw new IllegalArgumentException("Plans inválido");
    }
}