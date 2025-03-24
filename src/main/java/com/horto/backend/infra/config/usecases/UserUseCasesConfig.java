package com.horto.backend.infra.config.usecases;

import com.horto.backend.core.usecases.user.get.*;
import com.horto.backend.core.usecases.user.post.RegisterUserCase;
import com.horto.backend.core.usecases.user.post.RegisterUserCaseImpl;
import com.horto.backend.infra.gateway.UserRepoGateway;
import com.horto.backend.infra.persistence.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class UserUseCasesConfig {

    private final UserRepoGateway userRepoGateway;


    @Bean
    public RegisterUserCase registerUserCase() {
        return new RegisterUserCaseImpl(userRepoGateway);
    }

    @Bean
    public GetUserByEmailCase getUserByEmailCase() {
        return new GetUserByEmailCaseImpl(userRepoGateway);
    }

    @Bean
    public GetUserByCnpjCase getUserByCnpjCase() {
        return new GetUserByCnpjCaseImpl(userRepoGateway);
    }

    @Bean
    public GetUserByPhoneCase getUserByPhoneCase() {
        return new GetUserByPhoneCaseImpl(userRepoGateway);
    }

    @Bean
    public GetUserByUsernameCase getUserByUsernameCase() {
        return new GetUserByUsernameCaseImpl(userRepoGateway);
    }

    @Bean
    public GetUserByIdCase getUserByIdCase() {
        return new GetUserByIdCaseImpl(userRepoGateway);
    }
}
