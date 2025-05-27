package dmitr.stockControl.stockService.exception.base;

import lombok.Getter;

@Getter
public abstract class CommonException extends RuntimeException {

    private final String i18nMessageKey;
    private final Object[] i18nArgs;

    public CommonException(String i18nMessageKey, Object[] i18nArgs) {
        this.i18nMessageKey = i18nMessageKey;
        this.i18nArgs = i18nArgs;
    }

    public CommonException(String i18nMessageKey) {
        this(i18nMessageKey, null);
    }
}
