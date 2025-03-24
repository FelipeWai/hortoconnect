package com.horto.backend.infra.gateway;

import com.horto.backend.core.entities.User;
import com.horto.backend.core.enums.UserRoleEnum;
import com.horto.backend.core.exceptions.user.alreadyExists.CnpjAlreadyExistsException;
import com.horto.backend.core.exceptions.user.alreadyExists.EmailAlreadyExistsException;
import com.horto.backend.core.exceptions.user.alreadyExists.PhoneAlreadyExistsException;
import com.horto.backend.core.exceptions.user.alreadyExists.UsernameAlreadyExistsException;
import com.horto.backend.core.gateway.UserGateway;
import com.horto.backend.infra.dto.user.request.RegisterRequestDTO;
import com.horto.backend.infra.mapper.UserMapper;
import com.horto.backend.infra.persistence.entities.UserEntity;
import com.horto.backend.infra.persistence.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserRepoGateway implements UserGateway {

    private final UserRepository userRepository;

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<User> getUserByEmail(String email) {
        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(email);
        return userEntityOptional.map(userMapper::toDomain);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(id);
        return userEntityOptional.map(userMapper::toDomain);
    }

    @Override
    public Optional<User> getUserByCnpj(String cnpj) {
        Optional<UserEntity> userEntityOptional = userRepository.findByCnpj(cnpj);
        if (userEntityOptional.isPresent()) {
            return Optional.of(userMapper.toDomain(userEntityOptional.get()));
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> getUserByPhone(String phone) {
        Optional<UserEntity> userEntityOptional = userRepository.findByPhone(phone);
        if (userEntityOptional.isPresent()) {
            return Optional.of(userMapper.toDomain(userEntityOptional.get()));
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        Optional<UserEntity> userEntityOptional = userRepository.findByUsername(username);
        if (userEntityOptional.isPresent()) {
            return Optional.of(userMapper.toDomain(userEntityOptional.get()));
        }
        return Optional.empty();
    }

    @Override
    public User registerUser(RegisterRequestDTO registerRequestDTO) {
        if (registerRequestDTO.role() == UserRoleEnum.ADMIN) {
            throw new IllegalArgumentException("Não é permitido criar usuários com a role ADMIN.");
        }

        String phoneSanitized = registerRequestDTO.phone().replaceAll("\\s+", "");
        String emailLowerCase = registerRequestDTO.email().trim().toLowerCase();
        String usernameLowerCase = registerRequestDTO.username().trim().toLowerCase();

        if(getUserByEmail(emailLowerCase).isPresent()) {
            throw new EmailAlreadyExistsException(emailLowerCase);
        }
        if(getUserByCnpj(registerRequestDTO.cnpj()).isPresent()) {
            throw new CnpjAlreadyExistsException(registerRequestDTO.cnpj());
        }
        if(getUserByPhone(phoneSanitized).isPresent()) {
            throw new PhoneAlreadyExistsException(phoneSanitized);
        }
        if(getUserByUsername(usernameLowerCase).isPresent()) {
            throw new UsernameAlreadyExistsException(usernameLowerCase);
        }



        UserEntity entity = userMapper.toEntity(registerRequestDTO);
        entity.setEmail(emailLowerCase);
        entity.setUsername(usernameLowerCase);
        entity.setPhone(phoneSanitized);

        String encodedPassword = passwordEncoder.encode(registerRequestDTO.password());
        entity.setPassword(encodedPassword);

        UserEntity savedEntity = userRepository.save(entity);
        return userMapper.toDomain(savedEntity);
    }
}
