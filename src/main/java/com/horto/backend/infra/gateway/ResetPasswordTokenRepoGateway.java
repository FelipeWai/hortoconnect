package com.horto.backend.infra.gateway;

import com.horto.backend.core.entities.ResetPasswordToken;
import com.horto.backend.core.entities.User;
import com.horto.backend.core.exceptions.resetPasswordToken.NewPasswordSameAsOldException;
import com.horto.backend.core.exceptions.resetPasswordToken.TokenNotFoundException;
import com.horto.backend.core.gateway.ResetPasswordTokenGateway;
import com.horto.backend.core.usecases.user.get.GetUserByEmailCase;
import com.horto.backend.infra.dto.resetPassword.request.NewPasswordDTO;
import com.horto.backend.infra.mapper.ResetPasswordTokenMapper;
import com.horto.backend.infra.mapper.UserMapper;
import com.horto.backend.infra.persistence.entities.ResetPasswordTokenEntity;
import com.horto.backend.infra.persistence.entities.UserEntity;
import com.horto.backend.infra.persistence.repositories.ResetPasswordTokenRepository;
import com.horto.backend.infra.persistence.repositories.UserRepository;
import com.horto.backend.infra.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.*;

@Component
@RequiredArgsConstructor
public class ResetPasswordTokenRepoGateway implements ResetPasswordTokenGateway {

    private final ResetPasswordTokenRepository resetPasswordTokenRepository;
    private final UserRepository userRepository;

    private final GetUserByEmailCase getUserByEmailCase;

    private final ResetPasswordTokenMapper resetPasswordTokenMapper;
    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    private final EmailService emailService;

    @Override
    public void GenerateResetPasswordToken(String email) {
        User user = getUserByEmailCase.execute(email);

        SecureRandom secureRandom = new SecureRandom();
        String token = String.format("%06d", secureRandom.nextInt(1_000_000));

        LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(10);

        ResetPasswordToken resetToken = new ResetPasswordToken(email, token, expirationTime);

        resetPasswordTokenRepository.save(resetPasswordTokenMapper.toEntity(resetToken));

        Map<String, Object> templateModel = new HashMap<>();
        templateModel.put("username", user.username());
        templateModel.put("token", token);

        emailService.sendHtmlEmail("felipe.losadawai0@gmail.com", "Reset Password Token", templateModel);
    }


    @Override
    public Boolean VerifyResetPasswordToken(String email, String token) {
        LocalDateTime now = LocalDateTime.now();

        ResetPasswordToken resetPasswordToken = FindTokenByEmailAndToken(email, token)
                .orElseThrow(TokenNotFoundException::new);

        return now.isBefore(resetPasswordToken.expirationTime()) && !resetPasswordToken.used();
    }

    @Override
    public Optional<ResetPasswordToken> FindTokenByEmailAndToken(String email, String token) {
        Optional<ResetPasswordTokenEntity> tokenEntity = resetPasswordTokenRepository.findByEmailAndToken(email, token);
        return tokenEntity.map(resetPasswordTokenMapper::toDomain);
    }

    @Override
    public void resetPassword(NewPasswordDTO newPasswordDTO) {

        ResetPasswordToken resetPasswordToken = FindTokenByEmailAndToken(newPasswordDTO.email(), newPasswordDTO.token())
                .orElseThrow(TokenNotFoundException::new);

        if (LocalDateTime.now().isAfter(resetPasswordToken.expirationTime()) || resetPasswordToken.used()) {
            throw new TokenNotFoundException();
        }

        ResetPasswordTokenEntity resetPasswordTokenEntity = resetPasswordTokenMapper.toEntity(resetPasswordToken);
        resetPasswordTokenEntity.setUsed(true);
        resetPasswordTokenRepository.save(resetPasswordTokenEntity);

        User user = getUserByEmailCase.execute(newPasswordDTO.email());
        if (passwordEncoder.matches(newPasswordDTO.newPassword(), user.password())) {
            throw new NewPasswordSameAsOldException();
        }

        UserEntity userEntity = userMapper.toEntity(user);
        userEntity.setPassword(passwordEncoder.encode(newPasswordDTO.newPassword()));
        userRepository.save(userEntity);
    }

    @Scheduled(fixedRate = 86400000)
    public void cleanExpiredTokens() {
        LocalDateTime now = LocalDateTime.now();
        int deletedTokens = resetPasswordTokenRepository.deleteByExpirationTimeBefore(now);
        System.out.println("Tokens expirados removidos: " + deletedTokens);
    }
}
