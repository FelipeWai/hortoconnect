//package com.horto.backend.infra.presentation.pagamento;
//
//import com.horto.backend.infra.service.MercadoPagoService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Date;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/webhook")
//@RequiredArgsConstructor
//public class WebhookController {
//
//    private final MercadoPagoService mercadoPagoService;
//
//    @PostMapping
//    public ResponseEntity<Void> handleWebhook(@RequestBody Map<String, Object> payload) {
//        System.out.println("Webhook recebido às " + new Date());
//        System.out.println("Payload: " + payload);
//
//        String eventType = (String) payload.get("type");
//
//        if ("payment".equals(eventType)) {
//            Map<String, Object> data = (Map<String, Object>) payload.get("data");
//            String paymentId = data.get("id").toString();
//
//            mercadoPagoService.confirmarPagamento(paymentId);
//        }
//
//        return ResponseEntity.ok().build();
//    }
//}