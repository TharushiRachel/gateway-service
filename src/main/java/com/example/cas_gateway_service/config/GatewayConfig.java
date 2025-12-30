package com.example.cas_gateway_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

/**
 * Gateway Configuration for CORS and global settings.
 * This service acts as an ingress controller to route traffic to backend services.
 */
@Configuration
public class GatewayConfig {

    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        // Note: Cannot use "*" with allowCredentials=true
        // For production, specify exact origins: Arrays.asList("https://yourdomain.com", "https://anotherdomain.com")
        corsConfig.setAllowedOriginPatterns(Arrays.asList("*")); // Use patterns instead for wildcard with credentials
        corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        corsConfig.setAllowedHeaders(Arrays.asList("*"));
        corsConfig.setExposedHeaders(Arrays.asList("*"));
        corsConfig.setAllowCredentials(true);
        corsConfig.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        return new CorsWebFilter(source);
    }
}

