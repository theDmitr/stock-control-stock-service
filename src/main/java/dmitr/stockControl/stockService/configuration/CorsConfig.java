package dmitr.stockControl.stockService.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;

@Configuration
public class CorsConfig {

    @Bean
    @ConfigurationProperties(prefix = "app.cors")
    public CorsConfigurationProperties corsConfigurationProperties() {
        return new CorsConfigurationProperties();
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfigurationProperties properties = corsConfigurationProperties();

        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(properties.isAllowCredentials());
        config.setMaxAge(properties.getMaxAge());
        config.setAllowedOrigins(properties.getAllowedOrigins());
        config.setAllowedMethods(Arrays.asList(properties.getAllowedMethods().split(",")));
        config.setAllowedHeaders(Arrays.asList(properties.getAllowedHeaders().split(",")));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }

    @Getter
    @Setter
    public static class CorsConfigurationProperties {
        private List<String> allowedOrigins;
        private String allowedMethods;
        private String allowedHeaders;
        private boolean allowCredentials;
        private long maxAge;
    }
}
