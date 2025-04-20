package com.horto.backend.infra.config.aws.secrets;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class DataSourceConfig {

    private final AWSSecretManagerService awsSecretManagerService;

    @Bean
    public DataSource dataSource() {
        JsonNode secret = awsSecretManagerService.getSecret("horto-dev-db");

        String host = getJsonValue(secret, "host");
        String port = getJsonValue(secret, "port");
        String username = getJsonValue(secret, "username");
        String password = getJsonValue(secret, "password");

        String url = String.format("jdbc:postgresql://%s:%s/%s", host, port, "postgres");

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        return dataSource;
    }

    private String getJsonValue(JsonNode node, String key) {
        return Optional.ofNullable(node.get(key))
                .map(JsonNode::asText)
                .orElseThrow(() -> new IllegalStateException("Campo ausente no segredo: " + key));
    }
}