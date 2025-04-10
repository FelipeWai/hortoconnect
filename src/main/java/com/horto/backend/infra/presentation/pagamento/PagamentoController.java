package com.horto.backend.infra.presentation.pagamento;

import com.horto.backend.core.exceptions.payment.InvalidPaymentMethodException;
import com.horto.backend.infra.config.security.TokenService;
import com.horto.backend.infra.dto.pagamento.PaymentDTO;
import com.horto.backend.infra.service.MercadoPagoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PagamentoController {

    private final MercadoPagoService mercadoPagoService;
    private final TokenService tokenService;

    @PostMapping("/{method}")
    public ResponseEntity<?> createPayment(
            @PathVariable String method,
            @RequestBody PaymentDTO dto,
            @RequestHeader("Authorization") String authHeader) {

        String token = authHeader.replace("Bearer ", "");
        String tokenEmail = tokenService.getEmailFromToken(token);

        if (tokenEmail == null || !tokenEmail.equals(dto.userEmail())) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(Map.of("error", "O email fornecido não corresponde ao usuário autenticado"));
        }

        switch (method) {
            case "card" -> {
                Map<String, Object> resposta = mercadoPagoService.criarPagamentoCartao(dto);
                return ResponseEntity.ok(resposta);
            }
//            case "pix" -> {
//                Map<String, Object> resposta = mercadoPagoService.criarPagamentoPix(dto);
//                return ResponseEntity.ok(resposta);
//            }
            default -> throw new InvalidPaymentMethodException(method);
        }
    }
}
