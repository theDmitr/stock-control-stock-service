package dmitr.stockControl.stockService.exception.extended.stockHolder;

import dmitr.stockControl.stockService.exception.base.NotFoundException;

public class NotFoundStockHolderException extends NotFoundException {

    public NotFoundStockHolderException() {
        super("stockHolder.notFound");
    }
}
