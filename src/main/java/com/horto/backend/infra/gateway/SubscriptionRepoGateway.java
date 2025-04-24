package com.horto.backend.infra.gateway;

import com.horto.backend.core.entities.Subscription;
import com.horto.backend.core.entities.User;
import com.horto.backend.core.enums.SubscriptionStatus;
import com.horto.backend.core.exceptions.payment.InvalidPaymentException;
import com.horto.backend.core.exceptions.user.notFound.UserNotFoundByIdException;
import com.horto.backend.core.gateway.SubscriptionGateway;
import com.horto.backend.infra.mapper.SubscriptionMapper;
import com.horto.backend.infra.mapper.UserMapper;
import com.horto.backend.infra.persistence.entities.PaymentEntity;
import com.horto.backend.infra.persistence.entities.SubscriptionEntity;
import com.horto.backend.infra.persistence.entities.UserEntity;
import com.horto.backend.infra.persistence.repositories.PaymentRepository;
import com.horto.backend.infra.persistence.repositories.SubscriptionRepository;
import com.horto.backend.infra.persistence.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Transactional
public class SubscriptionRepoGateway implements SubscriptionGateway {

    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final SubscriptionMapper subscriptionMapper;
    private final UserMapper userMapper;

    @Override
    public void createOrUpdateSubscription(Long userId, Long planId) {
        Optional<SubscriptionEntity> subscriptionOptional = subscriptionRepository.findByUser_Id(userId);
        List<PaymentEntity> paymentEntityList = paymentRepository.findByUser_IdOrderByPaymentDateDesc(userId);
        if (paymentEntityList.isEmpty()) {
            throw new InvalidPaymentException("Pagamento não encontrado");
        }else{
            if (subscriptionOptional.isPresent()) {
                SubscriptionEntity subscriptionEntity = subscriptionOptional.get();
                subscriptionEntity.setStartDate(LocalDateTime.now());
                subscriptionEntity.setEndDate(LocalDateTime.now().plusDays(30));
                subscriptionEntity.setStatus(SubscriptionStatus.ACTIVE);
                subscriptionEntity.setPlanId(planId);
                subscriptionEntity.setLastPayment(paymentEntityList.get(0));
            }else{
                UserEntity user = userRepository.findById(userId)
                        .orElseThrow(() -> new UserNotFoundByIdException(userId.toString()));
                SubscriptionEntity subscriptionEntity = new SubscriptionEntity();
                subscriptionEntity.setUser(user);
                subscriptionEntity.setPlanId(planId);
                subscriptionEntity.setStartDate(LocalDateTime.now());
                subscriptionEntity.setEndDate(LocalDateTime.now().plusDays(30));
                subscriptionEntity.setStatus(SubscriptionStatus.ACTIVE);
                subscriptionEntity.setLastPayment(paymentEntityList.get(0));
                subscriptionRepository.save(subscriptionEntity);
            }
        }
    }

    @Override
    public Optional<Subscription> getSubscriptionByUserId(Long userId) {
        Optional<SubscriptionEntity> subscriptionOptional = subscriptionRepository.findByUser_Id(userId);
        return subscriptionOptional.map(subscriptionMapper::toDomain);
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void inactiveSubscriptions() {
        List<SubscriptionEntity> subscriptionEntityList = subscriptionRepository.findByEndDateBefore(LocalDateTime.now());
        for (SubscriptionEntity subscriptionEntity : subscriptionEntityList) {
            subscriptionEntity.setStatus(SubscriptionStatus.EXPIRED);
        }
    }
}
