package dmitr.stockControl.stockService.helper.i18n;

import dmitr.stockControl.stockService.exception.base.CommonException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Slf4j
@Component
@RequiredArgsConstructor
public class I18nMessageProvider {

    private final MessageSource messageSource;

    private static final String DEFAULT_ERROR_MESSAGE_KEY = "default";

    public String getMessage(CommonException e) {
        return getMessage(e.getI18nMessageKey(), e.getI18nArgs());
    }

    public String getMessage(String key, Object[] args) {
        return getMessage(key, args, Locale.getDefault());
    }

    public String getMessage(String key, Object[] args, Locale locale) {
        try {
            return messageSource.getMessage(key, args, locale);
        } catch (NoSuchMessageException messageException) {
            log.error("Сообщение об ошибке по ключу {} не найдено", key);
        }
        return messageSource.getMessage(DEFAULT_ERROR_MESSAGE_KEY, args, locale);
    }
}
