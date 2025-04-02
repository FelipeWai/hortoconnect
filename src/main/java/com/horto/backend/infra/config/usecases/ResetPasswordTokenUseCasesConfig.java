package com.horto.backend.infra.config.usecases;

import com.horto.backend.core.usecases.resetPasswordToken.get.FindTokenByEmailAndTokenCase;
import com.horto.backend.core.usecases.resetPasswordToken.get.FindTokenByEmailAndTokenCaseImpl;
import com.horto.backend.core.usecases.resetPasswordToken.post.*;
import com.horto.backend.infra.gateway.ResetPasswordTokenRepoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ResetPasswordTokenUseCasesConfig {

    private final ResetPasswordTokenRepoGateway resetPasswordTokenRepoGateway;

    @Bean
    public GenerateResetPasswordTokenUseCase generateResetPasswordTokenUseCase() {
        return new GenerateResetPasswordTokenUseCaseImpl(resetPasswordTokenRepoGateway);
    }

    @Bean
    public VerifyResetPasswordTokenUseCase verifyResetPasswordTokenUseCase() {
        return new VerifyResetPasswordTokenUseCaseImpl(resetPasswordTokenRepoGateway);
    }

    @Bean
    public FindTokenByEmailAndTokenCase findTokenByEmailAndTokenCase() {
        return new FindTokenByEmailAndTokenCaseImpl(resetPasswordTokenRepoGateway);
    }

    @Bean
    public ResetUserPasswordCase resetUserPasswordCase() {
        return new ResetUserPasswordCaseImpl(resetPasswordTokenRepoGateway);
    }

}
