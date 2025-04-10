package com.horto.backend.core.exceptions.payment;

public class InvalidPaymentMethodException extends RuntimeException {
    public InvalidPaymentMethodException(String metodo) {
        super("Método de pagamento inválido: " + metodo);
    }
}
