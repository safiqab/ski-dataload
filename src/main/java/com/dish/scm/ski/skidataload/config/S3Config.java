package com.dish.scm.ski.skidataload.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "ski.file")
@Slf4j
@Data
@NoArgsConstructor
public class S3Config {
    private final S3 s3 = new S3();
    private final Win_location win_location = new Win_location();
    private final Linux_location linux_location = new Linux_location();

    @Data
    public static class S3 {
        private String bucketname;
        private String insert_folder;
        private String archive_folder;
        private String error_folder;
        private String region;
    }

    @Data
    public static class Win_location {
        private String process;
        private String output;
    }

    @Data
    public static class Linux_location {
        private String process;
        private String output;
    }
}
