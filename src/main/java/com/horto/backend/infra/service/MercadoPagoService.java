package com.horto.backend.infra.service;

import com.horto.backend.core.enums.Plans;
import com.horto.backend.infra.dto.pagamento.PaymentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MercadoPagoService {

    private final WebClient mercadoPagoClient;

    public Map<String, Object> criarPagamentoCartao(PaymentDTO dto) {
        Map<String, Object> payer = Map.of(
                "email", dto.userEmail()
        );

        Map<String, Object> body = new HashMap<>();
        body.put("transaction_amount", Plans.getValorPorId(dto.planId()));
        body.put("token", dto.token());
        body.put("description", "Doação para ONG de Adoção");
        body.put("installments", 1);
        body.put("payment_method_id", dto.payment_method());
        body.put("payer", payer);

        try {
            return mercadoPagoClient.post()
                    .uri("/v1/payments")
                    .contentType(MediaType.APPLICATION_JSON)
                    .header("X-Idempotency-Key", UUID.randomUUID().toString())
                    .bodyValue(body)
                    .retrieve()
                    .onStatus(HttpStatusCode::isError, response ->
                            response.bodyToMono(String.class)
                                    .flatMap(error -> {
                                        System.out.println("Erro Mercado Pago: " + error);
                                        return Mono.error(new RuntimeException("Erro ao processar pagamento: " + error));
                                    })
                    )
                    .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
                    .block();
        } catch (Exception e) {
            throw new RuntimeException("Falha na comunicação com o Mercado Pago: " + e.getMessage());
        }
    }


}
