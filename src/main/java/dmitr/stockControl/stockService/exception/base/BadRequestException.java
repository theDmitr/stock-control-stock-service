package dmitr.stockControl.stockService.exception.base;

public class BadRequestException extends CommonException {

    public BadRequestException(String i18nMessageKey, Object[] i18nArgs) {
        super(i18nMessageKey, i18nArgs);
    }
}
