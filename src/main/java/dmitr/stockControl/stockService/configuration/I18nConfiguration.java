package dmitr.stockControl.stockService.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;

@Configuration
public class I18nConfiguration implements WebMvcConfigurer {

// todo ПОНЯТЬ КАК ЛОКАЛЬ ДИНАМИЧЕСКИ ПОДСТАВЛЯТЬ   @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(localeChangeInterceptor());
//    }
//
//    @Bean
//    public LocaleChangeInterceptor localeChangeInterceptor() {
//        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
//        interceptor.setParamName("lang");
//        return interceptor;
//    }
//
//    @Bean
//    public LocaleResolver localeResolver() {
//        final AcceptHeaderLocaleResolver resolver = new AcceptHeaderLocaleResolver();
//        resolver.setSupportedLocales(Arrays.asList(Locale.ENGLISH, Locale.of("ru")));
//        resolver.setDefaultLocale(Locale.ENGLISH);
//        return resolver;
//    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:i18n/messages");
        messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
        return messageSource;
    }
}
