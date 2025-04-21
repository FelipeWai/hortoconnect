package com.horto.backend.infra.config.aws.secrets;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class MercadoPagoTokenConfig {

    private final String secret;

    @Autowired
    public MercadoPagoTokenConfig(AWSSecretManagerService awsSecretManagerService) {
        JsonNode secretNode = awsSecretManagerService.getSecret("mercado-pago");
        this.secret = secretNode.get("token").asText();
    }

}