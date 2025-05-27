package dmitr.stockControl.stockService.client.itemService;

import feign.Logger;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ItemServiceConfiguration {

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

//todo AUTH    @Bean
//    public RequestInterceptor requestInterceptor() {
//        return template -> template.header("Authorization", "Bearer token");
//    }

    @Bean
    public Retryer retryer() {
        return new Retryer.Default();
    }
}
