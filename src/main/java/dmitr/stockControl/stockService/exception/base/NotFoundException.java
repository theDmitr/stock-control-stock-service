package dmitr.stockControl.stockService.exception.base;

public class NotFoundException extends CommonException {

    public NotFoundException(String i18nMessageKey, Object[] i18nArgs) {
        super(i18nMessageKey, i18nArgs);
    }

    public NotFoundException(String i18nMessageKey) {
        this(i18nMessageKey, null);
    }
}
