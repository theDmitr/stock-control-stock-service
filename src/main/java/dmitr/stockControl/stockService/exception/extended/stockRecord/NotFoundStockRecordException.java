package dmitr.stockControl.stockService.exception.extended.stockRecord;

import dmitr.stockControl.stockService.exception.base.NotFoundException;

public class NotFoundStockRecordException extends NotFoundException {

    public NotFoundStockRecordException() {
        super("stockRecord.notFound");
    }
}
