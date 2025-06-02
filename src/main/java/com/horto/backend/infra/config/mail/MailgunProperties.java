package com.horto.backend.infra.config.mail;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MailgunProperties {
    private String apiKey;
    private String domain;
    private String from;
}
