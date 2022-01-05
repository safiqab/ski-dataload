package com.dish.scm.ski.skidataload.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.util.StringUtils;

import java.nio.charset.Charset;
import java.util.Properties;

@Data
@Configuration
@Slf4j
public class EmailConfig {
    @Value("${email.from:}")
    private String from;
    @Value("${email.to:}")
    private String To;
    @Value("${email.cc:}")
    private String cc;
    @Value("${email.server.timeout:}")
    private String timeout;
    @Value("${email.server.host:}")
    private String emailHost;
    @Value("${email.server.port:}")
    private String emailPort;

    @Bean
    public JavaMailSender javaMailSender() {

        JavaMailSenderImpl mail = new JavaMailSenderImpl();
        mail.setHost(emailHost);
        mail.setPort(!StringUtils.isEmpty(emailPort) ? Integer.parseInt(emailPort):25);
        Properties props = mail.getJavaMailProperties();
        props.put("email.transport.protocol", "smtp");
        props.put("email.smtp.connectionTimeout",timeout);
        props.put("email.smtp.sendpartial","true");
        props.put("email.smtp.userset","true");
        props.put("email.smtp.charset", Charset.defaultCharset());
        props.put("email.smtp.auth", "false");
        props.put("email.smtp.starttls.enable", "true");
        props.put("email.debug", "true");
        mail.setJavaMailProperties(props);
        return mail;
    }
}
