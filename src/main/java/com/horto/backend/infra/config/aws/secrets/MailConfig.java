package com.horto.backend.infra.config.aws.secrets;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Optional;
import java.util.Properties;

@Configuration
@RequiredArgsConstructor
public class MailConfig {

    private final AWSSecretManagerService awsSecretManagerService;

    @Bean
    public JavaMailSender javaMailSender() {
        JsonNode secret = awsSecretManagerService.getSecret("Email-app-password");

        String host = getJsonValue(secret, "ses-smtp-host");
        int port = Integer.parseInt(getJsonValue(secret, "ses-smtp-port"));
        String username = getJsonValue(secret, "ses-smtp-username");
        String password = getJsonValue(secret, "ses-smtp-password");
        String auth = getJsonValue(secret, "auth");
        String starttls = getJsonValue(secret, "starttls");

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(username);
        mailSender.setPassword(password);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.auth", auth);
        props.put("mail.smtp.starttls.enable", starttls);
        props.put("mail.smtp.ssl.trust", host);

        return mailSender;
    }

    private String getJsonValue(JsonNode node, String key) {
        return Optional.ofNullable(node.get(key))
                .map(JsonNode::asText)
                .orElseThrow(() -> new IllegalStateException("Campo ausente no segredo: " + key));
    }
}
