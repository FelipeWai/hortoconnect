package com.horto.backend.infra.config.security;

import lombok.Builder;

@Builder
public record JWTUserData(Long id, String username, String email, String role) {
}