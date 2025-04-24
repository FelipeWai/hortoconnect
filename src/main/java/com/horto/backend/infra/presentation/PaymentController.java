package com.horto.backend.infra.presentation;

import com.horto.backend.core.entities.Payment;
import com.horto.backend.core.usecases.payment.pixPayment.ProcessPixPaymentUseCase;
import com.horto.backend.core.usecases.payment.pixPayment.UpdatePaymentStatusUseCase;
import com.horto.backend.infra.config.security.TokenService;
import com.horto.backend.infra.dto.pagamento.CardPaymentDTO;
import com.horto.backend.infra.dto.pagamento.CardPaymentResponseDTO;
import com.horto.backend.infra.dto.pagamento.PixPaymentDTO;
import com.horto.backend.infra.dto.pagamento.PixPaymentRespondeDTO;
import com.horto.backend.infra.mapper.PaymentMapper;
import com.horto.backend.infra.service.MercadoPagoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final ProcessPixPaymentUseCase processPixPaymentUseCase;
    private final UpdatePaymentStatusUseCase updatePaymentStatusUseCase;

    private final PaymentMapper paymentMapper;

    private final MercadoPagoService mercadoPagoService;
    private final TokenService tokenService;

    @PostMapping("/card")
    public ResponseEntity<CardPaymentResponseDTO> processPayment(
            @RequestBody @Valid CardPaymentDTO cardPaymentDTO,
            @RequestHeader("Authorization") String authHeader){

        String token = authHeader.replace("Bearer ", "");
        String tokenEmail = tokenService.getEmailFromToken(token);

        if (tokenEmail == null || !tokenEmail.equals(cardPaymentDTO.payer().email())) {
            throw new RuntimeException("Token invalido");
        }

        CardPaymentResponseDTO cardPayment = mercadoPagoService.createCardPayment(cardPaymentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(cardPayment);
    }

    @PostMapping("/pix")
    public ResponseEntity<PixPaymentRespondeDTO> processPixPayment(
            @RequestBody @Valid PixPaymentDTO pixPaymentDTO,
            @RequestHeader("Authorization") String authHeader) {

        String token = authHeader.replace("Bearer ", "");
        String tokenEmail = tokenService.getEmailFromToken(token);

        if (tokenEmail == null || !tokenEmail.equals(pixPaymentDTO.payer().email())) {
            throw new RuntimeException("Token inválido");
        }
        Payment payment = processPixPaymentUseCase.execute(pixPaymentDTO);
        PixPaymentRespondeDTO responseDTO = paymentMapper.toPixResponseDTO(payment);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PostMapping("/webhook")
    public ResponseEntity<String> handleWebhook(@RequestBody Map<String, Object> body,
                                                @RequestParam(required = false) String type,
                                                @RequestParam(value = "data.id", required = false) String dataId) {
        System.out.println("📩 Webhook recebido do Mercado Pago!");
        System.out.println("Tipo de evento: " + type);
        System.out.println("ID do dado: " + dataId);

        if ("payment".equals(type) && dataId != null) {
            try {
                Long pagamentoId = Long.parseLong(dataId);
                updatePaymentStatusUseCase.execute(pagamentoId);
            } catch (NumberFormatException e) {
                System.out.println("❌ ID do pagamento inválido: " + dataId);
            }
        }

        return ResponseEntity.ok("Webhook recebido com sucesso");
    }
}
