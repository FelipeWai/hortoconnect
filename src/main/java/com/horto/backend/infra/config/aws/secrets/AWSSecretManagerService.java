package com.horto.backend.infra.config.aws.secrets;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;

import java.io.IOException;

@Service
public class AWSSecretManagerService {

    private final SecretsManagerClient secretsManagerClient;
    private static final Region REGION = Region.US_EAST_2;

    public AWSSecretManagerService() {
        this.secretsManagerClient = SecretsManagerClient.builder()
                .region(REGION)
                .credentialsProvider(ProfileCredentialsProvider.create("horto"))
                .build();
    }

    public JsonNode getSecret(String secretName) {
        GetSecretValueRequest getSecretValueRequest = GetSecretValueRequest.builder()
                .secretId(secretName)
                .build();

        GetSecretValueResponse getSecretValueResponse = secretsManagerClient.getSecretValue(getSecretValueRequest);
        String secret = getSecretValueResponse.secretString();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readTree(secret);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao processar o segredo JSON", e);
        }
    }
}