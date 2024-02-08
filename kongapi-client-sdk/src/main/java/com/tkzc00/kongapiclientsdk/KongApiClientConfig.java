package com.tkzc00.kongapiclientsdk;

import com.tkzc00.kongapiclientsdk.client.KongApiClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "kongapi.client")
@Data
@ComponentScan
public class KongApiClientConfig {
    private String accessKey;
    private String secretKey;

    @Bean
    public KongApiClient kongApiClient() {
        return new KongApiClient(accessKey, secretKey);
    }
}
