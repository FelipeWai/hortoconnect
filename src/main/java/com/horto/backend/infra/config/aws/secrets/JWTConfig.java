package com.horto.backend.infra.config.aws.secrets;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JWTConfig {

    private final String secret;

    @Autowired
    public JWTConfig(AWSSecretManagerService awsSecretManagerService) {
        JsonNode secretNode = awsSecretManagerService.getSecret("horto-token-secret");
        this.secret = secretNode.get("secret").asText();
    }

    public String getSecret() { return secret; }

}
