package com.horto.backend.core.enums;

import java.math.BigDecimal;

public enum Plans {
    BASICO(1L, new BigDecimal("5.0"));

    private final Long id;
    private final BigDecimal valor;

    Plans(Long id, BigDecimal valor) {
        this.id = id;
        this.valor = valor;
    }

    public static BigDecimal getValorPorId(Long id) {
        for (Plans plano : values()) {
            if (plano.id.equals(id)) {
                return plano.valor;
            }
        }
        throw new IllegalArgumentException("Plans inválido");
    }
}