package dmitr.stockControl.stockService.exception.extended;

import dmitr.stockControl.stockService.exception.base.BadRequestException;

public class ValidationException extends BadRequestException {

    public ValidationException(String i18nMessageKey, Object[] i18nArgs) {
        super(i18nMessageKey, i18nArgs);
    }

    public ValidationException(String i18nMessageKey) {
        this(i18nMessageKey, null);
    }

    public ValidationException() {
        this("badRequest");
    }
}
