package com.horto.backend.infra.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.horto.backend.infra.config.aws.secrets.JWTConfig;
import com.horto.backend.infra.persistence.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TokenService {

    private final JWTConfig jwtConfig;

    public String generateToken(UserEntity user) {
        Algorithm algorithm = Algorithm.HMAC256(jwtConfig.getSecret());

        return JWT.create()
                .withSubject(user.getEmail())
                .withClaim("id", user.getId())
                .withClaim("username", user.getUsername())
                .withClaim("role", user.getRole().toString())
                .withExpiresAt(Instant.now().plusSeconds(86400))
                .withIssuedAt(Instant.now())
                .withIssuer("Hortoconnect")
                .sign(algorithm);
    }


    public Optional<JWTUserData> verifyToken(String token) {
        try{
            Algorithm algorithm = Algorithm.HMAC256(jwtConfig.getSecret());

            DecodedJWT jwt = JWT.require(algorithm)
                    .withIssuer("Hortoconnect")
                    .build()
                    .verify(token);

            if(!jwt.getIssuer().equals("Hortoconnect")){
                throw new JWTVerificationException("Token invalido");
            }

            return Optional.of(JWTUserData
                    .builder()
                    .id(jwt.getClaim("id").asLong())
                    .username(jwt.getClaim("username").asString())
                    .email(jwt.getSubject())
                    .role(jwt.getClaim("role").asString())
                    .build());
        }catch(JWTVerificationException e){
            return Optional.empty();
        }
    }

    public String getRoleFromToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtConfig.getSecret());

            DecodedJWT jwt = JWT.require(algorithm)
                    .withIssuer("Hortoconnect")
                    .build()
                    .verify(token);

            if(!jwt.getIssuer().equals("Hortoconnect")){
                throw new JWTVerificationException("Token invalido");
            }

            return jwt.getClaim("role").asString();

        } catch (JWTVerificationException e) {
            return null;
        }
    }

}