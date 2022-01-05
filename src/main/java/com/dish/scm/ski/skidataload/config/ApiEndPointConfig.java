package com.dish.scm.ski.skidataload.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "restapi.endpoint")
@Data
@NoArgsConstructor

public class ApiEndPointConfig {

    private final Skireference skireference = new Skireference();
    private final Skimodel skimodel = new Skimodel();
    private final Skisku skisku = new Skisku();

    @Data
    public static class Skireference {
        private String get_reference_url;
        private String patch_reference_url;
        private String post_reference_url;
    }

    @Data
    public static class Skimodel {
        private String get_model_url;
        private String post_model_url;
        private String patch_model_url;
    }

    @Data
    public static class Skisku {
        private String get_sku_url;
        private String post_sku_url;
        private String patch_sku_url;
    }
}
