package com.horto.backend.infra.service;

import com.horto.backend.core.entities.Subscription;
import com.horto.backend.core.enums.PaymentStatus;
import com.horto.backend.core.enums.Plans;
import com.horto.backend.core.exceptions.payment.InvalidPaymentException;
import com.horto.backend.core.exceptions.user.notFound.UsernameNotFoundException;
import com.horto.backend.core.usecases.subscription.CreateOrUpdateSubscriptionUseCase;
import com.horto.backend.core.usecases.subscription.GetSubscriptionByUserIdUseCase;
import com.horto.backend.infra.config.aws.secrets.MercadoPagoTokenConfig;
import com.horto.backend.infra.dto.pagamento.CardPaymentDTO;
import com.horto.backend.infra.dto.pagamento.CardPaymentResponseDTO;
import com.horto.backend.infra.dto.pagamento.PixPaymentDTO;
import com.horto.backend.infra.exception.pagamento.MercadoPagoException;
import com.horto.backend.infra.persistence.entities.PaymentEntity;
import com.horto.backend.infra.persistence.entities.UserEntity;
import com.horto.backend.infra.persistence.repositories.PaymentRepository;
import com.horto.backend.infra.persistence.repositories.UserRepository;
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

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MercadoPagoService {
    private final MercadoPagoTokenConfig mercadoPagoConfig;

    private final UserRepository userRepository;
    private final PaymentRepository paymentRepository;

    private final CreateOrUpdateSubscriptionUseCase createOrUpdateSubscriptionUseCase;

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

    public PaymentEntity createPixPayment(PixPaymentDTO pixPaymentDTO) {
        try{
            MercadoPagoConfig.setAccessToken(mercadoPagoConfig.getSecret());

            UserEntity userEntity = userRepository.findByEmail(pixPaymentDTO.payer().email())
                    .orElseThrow(() -> new UsernameNotFoundException(pixPaymentDTO.payer().email()));

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
                            .description(pixPaymentDTO.description())
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

            PaymentEntity paymentEntity = new PaymentEntity();
            paymentEntity.setUser(userEntity);
            paymentEntity.setExternalPaymentId(createdPayment.getId());
            paymentEntity.setAmount(createdPayment.getTransactionAmount());
            paymentEntity.setPlanId(pixPaymentDTO.planId());
            paymentEntity.setPaymentMethod("PIX");
            paymentEntity.setStatus(PaymentStatus.PENDING);
            paymentEntity.setStatusDetail(createdPayment.getStatusDetail());
            paymentEntity.setQrCode(createdPayment.getPointOfInteraction().getTransactionData().getQrCode());
            paymentEntity.setQrCodeBase64(createdPayment.getPointOfInteraction().getTransactionData().getQrCodeBase64());
            paymentEntity.setDescription(pixPaymentDTO.description());

            return paymentEntity;

        } catch (MPApiException apiException) {
            System.out.println(apiException.getApiResponse().getContent());
            throw new MercadoPagoException(apiException.getApiResponse().getContent());
        } catch (MPException exception) {
            System.out.println(exception.getMessage());
            throw new MercadoPagoException(exception.getMessage());
        }
    }


    public PaymentEntity consultPayment(Long externalPaymentId) {
        try {
            MercadoPagoConfig.setAccessToken(mercadoPagoConfig.getSecret());

            PaymentClient client = new PaymentClient();
            Payment mpPayment = client.get(externalPaymentId);

            System.out.println("Status: " + mpPayment.getStatus());
            System.out.println("StatusDetail: " + mpPayment.getStatusDetail());
            System.out.println("Valor: " + mpPayment.getTransactionAmount());
            System.out.println("Email do pagador: " + mpPayment.getPayer().getEmail());
            System.out.println("Data da aprovação: " + mpPayment.getDateApproved());

            PaymentEntity paymentEntity = paymentRepository.findByExternalPaymentId(externalPaymentId)
                    .orElseThrow(() -> new InvalidPaymentException("Pagamento não encontrado em nosso banco"));

            PaymentStatus newStatus = mapMercadoPagoStatus(mpPayment.getStatus());

            paymentEntity.setStatus(newStatus);
            paymentEntity.setStatusDetail(mpPayment.getStatusDetail());

            if (newStatus == PaymentStatus.CONFIRMED) {
                if (mpPayment.getDateApproved() != null) {
                    paymentEntity.setPaymentDate(LocalDateTime.ofInstant(
                            mpPayment.getDateApproved().toInstant(),
                            ZoneId.systemDefault()));
                } else {
                    paymentEntity.setPaymentDate(LocalDateTime.now());
                }
                createOrUpdateSubscriptionUseCase.execute(paymentEntity.getUser().getId(), paymentEntity.getPlanId());
            }

            return paymentEntity;
        } catch (MPApiException apiException) {
            System.out.println(apiException.getApiResponse().getContent());
            throw new MercadoPagoException(apiException.getApiResponse().getContent());
        } catch (MPException exception) {
            System.out.println(exception.getMessage());
            throw new MercadoPagoException(exception.getMessage());
        }
    }

    private PaymentStatus mapMercadoPagoStatus(String mpStatus) {
        switch (mpStatus.toLowerCase()) {
            case "approved":
                return PaymentStatus.CONFIRMED;
            case "pending":
                return PaymentStatus.PENDING;
            case "rejected":
                return PaymentStatus.FAILED;
            case "canceled":
                return PaymentStatus.FAILED;
            case "refunded":
                return PaymentStatus.REFUNDED;
            case "in_process":
                return PaymentStatus.PENDING;
            default:
                return PaymentStatus.PENDING;
        }
    }
}