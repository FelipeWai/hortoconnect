package com.horto.backend.infra.config.security;

import com.horto.backend.core.entities.Subscription;
import com.horto.backend.core.enums.SubscriptionStatus;
import com.horto.backend.infra.persistence.entities.SubscriptionEntity;
import com.horto.backend.infra.persistence.repositories.SubscriptionRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final SubscriptionRepository subscriptionRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authorizationHeader.substring("Bearer ".length());
        Optional<JWTUserData> optionalJWTUserData = tokenService.verifyToken(token);

        if (optionalJWTUserData.isPresent()) {
            JWTUserData jwtUserData = optionalJWTUserData.get();
            String role = tokenService.getRoleFromToken(token); // ex: "ADMIN", "USER", etc.

            // Se não for ADMIN, verifica assinatura
            if (!"ADMIN".equalsIgnoreCase(role)) {
                var subscriptionOpt = subscriptionRepository.findByUser_Id(jwtUserData.id());

                if (subscriptionOpt.isEmpty() || subscriptionOpt.get().getStatus() != SubscriptionStatus.ACTIVE) {
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    response.getWriter().write("Acesso negado: assinatura inativa ou inexistente.");
                    return;
                }
            }

            var authorities = List.of(new SimpleGrantedAuthority("ROLE_" + role));
            var authenticationToken = new UsernamePasswordAuthenticationToken(jwtUserData, null, authorities);

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        filterChain.doFilter(request, response);
    }
}