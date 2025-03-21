package com.horto.backend.infra.presentation;

import com.horto.backend.core.entities.User;
import com.horto.backend.core.exceptions.user.UsernameOrPasswordInvalidException;
import com.horto.backend.core.usecases.user.post.RegisterUserCase;
import com.horto.backend.infra.config.security.JWTUserData;
import com.horto.backend.infra.config.security.TokenService;
import com.horto.backend.infra.dto.user.request.LoginRequestDTO;
import com.horto.backend.infra.dto.user.request.RegisterRequestDTO;
import com.horto.backend.infra.dto.user.response.LoginResponseDTO;
import com.horto.backend.infra.dto.user.response.UserResponseDTO;
import com.horto.backend.infra.mapper.UserMapper;
import com.horto.backend.infra.persistence.entities.UserEntity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    private final RegisterUserCase registerUserCase;

    private final UserMapper userMapper;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> registerUser(@RequestBody @Valid RegisterRequestDTO registerRequestDTO) {
        User newUser = registerUserCase.execute(registerRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(userMapper.toResponseDTO(newUser));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginRequestDTO loginRequestDTO) {
        try{
            UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(loginRequestDTO.email(), loginRequestDTO.password());
            Authentication authenticate = authenticationManager.authenticate(userAndPass);

            UserEntity user = (UserEntity) authenticate.getPrincipal();
            String token = tokenService.generateToken(user);

            return ResponseEntity.ok(new LoginResponseDTO(token));

        }catch (BadCredentialsException e){
            throw new UsernameOrPasswordInvalidException("Usuário ou senha inválido");
        }
    }
}
