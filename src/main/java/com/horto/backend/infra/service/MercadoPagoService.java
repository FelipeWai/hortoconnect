package com.horto.backend.infra.service;

import com.horto.backend.core.enums.Plans;
import com.horto.backend.infra.config.aws.secrets.MercadoPagoTokenConfig;
import com.horto.backend.infra.dto.pagamento.CardPaymentDTO;
import com.horto.backend.infra.dto.pagamento.CardPaymentResponseDTO;
import com.horto.backend.infra.dto.pagamento.PixPaymentDTO;
import com.horto.backend.infra.dto.pagamento.PixPaymentRespondeDTO;
import com.horto.backend.infra.exception.pagamento.MercadoPagoException;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.common.IdentificationRequest;
import com.mercadopago.client.payment.PaymentClient;
import com.mercadopago.client.payment.PaymentCreateRequest;
import com.mercadopago.client.payment.PaymentPayerRequest;
import com.mercadopago.core.MPRequestOptions;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.payment.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MercadoPagoService {
    private final MercadoPagoTokenConfig mercadoPagoConfig;

    public CardPaymentResponseDTO createCardPayment(CardPaymentDTO cardPaymentDTO) {
        try {
            MercadoPagoConfig.setAccessToken(mercadoPagoConfig.getSecret());

            PaymentClient paymentClient = new PaymentClient();

            PaymentCreateRequest paymentCreateRequest =
                    PaymentCreateRequest.builder()
                            .transactionAmount(Plans.getValorPorId(cardPaymentDTO.planId()))
                            .token(cardPaymentDTO.token())
                            .description(cardPaymentDTO.description())
                            .installments(cardPaymentDTO.installments())
                            .paymentMethodId(cardPaymentDTO.paymentMethodId())
                            .payer(
                                    PaymentPayerRequest.builder()
                                            .email(cardPaymentDTO.payer().email())
                                            .identification(
                                                    IdentificationRequest.builder()
                                                            .type(cardPaymentDTO.payer().identification().type())
                                                            .number(cardPaymentDTO.payer().identification().number())
                                                            .build())
                                            .build())
                            .build();

            Payment createdPayment = paymentClient.create(paymentCreateRequest);

            return new CardPaymentResponseDTO(
                    createdPayment.getId(),
                    String.valueOf(createdPayment.getStatus()),
                    createdPayment.getStatusDetail());

        } catch (MPApiException apiException) {
            System.out.println(apiException.getApiResponse().getContent());
            throw new MercadoPagoException(apiException.getApiResponse().getContent());
        } catch (MPException exception) {
            System.out.println(exception.getMessage());
            throw new MercadoPagoException(exception.getMessage());
        }
    }

    public PixPaymentRespondeDTO createPixPayment(PixPaymentDTO pixPaymentDTO) {
        try{
            MercadoPagoConfig.setAccessToken(mercadoPagoConfig.getSecret());

            Map<String, String> customHeaders = new HashMap<>();
            customHeaders.put("x-idempotency-key", UUID.randomUUID().toString());

            MPRequestOptions requestOptions = MPRequestOptions.builder()
                    .customHeaders(customHeaders)
                    .build();

            PaymentClient client = new PaymentClient();

            OffsetDateTime now = OffsetDateTime.now();

            PaymentCreateRequest paymentCreateRequest =
                    PaymentCreateRequest.builder()
                            .transactionAmount(Plans.getValorPorId(pixPaymentDTO.planId()))
                            .description("Título do produto")
                            .paymentMethodId(pixPaymentDTO.paymentMethodId())
                            .dateOfExpiration(now.plusMinutes(15))
                            .payer(
                                    PaymentPayerRequest.builder()
                                            .email(pixPaymentDTO.payer().email())
                                            .firstName(pixPaymentDTO.payer().email())
                                            .identification(
                                                    IdentificationRequest.builder()
                                                            .type(pixPaymentDTO.payer().identification().type())
                                                            .number(pixPaymentDTO.payer().identification().number())
                                                            .build())
                                            .build())
                            .build();

            Payment createdPayment = client.create(paymentCreateRequest, requestOptions);

            return new PixPaymentRespondeDTO(
                    createdPayment.getId(),
                    createdPayment.getStatus(),
                    createdPayment.getStatusDetail(),
                    createdPayment.getPointOfInteraction().getTransactionData().getQrCodeBase64(),
                    createdPayment.getPointOfInteraction().getTransactionData().getQrCode());

        } catch (MPApiException apiException) {
            System.out.println(apiException.getApiResponse().getContent());
            throw new MercadoPagoException(apiException.getApiResponse().getContent());
        } catch (MPException exception) {
            System.out.println(exception.getMessage());
            throw new MercadoPagoException(exception.getMessage());
        }
    }


    public void consultarPagamento(Long pagamentoId) {
        try {
            MercadoPagoConfig.setAccessToken(mercadoPagoConfig.getSecret());

            PaymentClient client = new PaymentClient();
            Payment pagamento = client.get(pagamentoId);

            System.out.println("✅ Status: " + pagamento.getStatus());
            System.out.println("🔍 StatusDetail: " + pagamento.getStatusDetail());
            System.out.println("💸 Valor: " + pagamento.getTransactionAmount());
            System.out.println("👤 Email do pagador: " + pagamento.getPayer().getEmail());
            System.out.println("🗓️ Data da aprovação: " + pagamento.getDateApproved());

        } catch (MPApiException apiException) {
            System.out.println(apiException.getApiResponse().getContent());
            throw new MercadoPagoException(apiException.getApiResponse().getContent());
        } catch (MPException exception) {
            System.out.println(exception.getMessage());
            throw new MercadoPagoException(exception.getMessage());
        }
    }
}