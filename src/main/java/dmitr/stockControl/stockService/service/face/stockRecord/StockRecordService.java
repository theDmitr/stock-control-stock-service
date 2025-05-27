package dmitr.stockControl.stockService.service.face.stockRecord;

import dmitr.stockControl.stockService.dao.entity.stockRecord.StockRecordId;
import dmitr.stockControl.stockService.model.stockRecord.StockRecordCreateDto;
import dmitr.stockControl.stockService.model.stockRecord.StockRecordDto;
import dmitr.stockControl.stockService.model.stockRecord.StockRecordUpdateDto;

import java.util.List;

public interface StockRecordService {

    List<StockRecordDto> getStockRecords();
    StockRecordDto getStockRecord(StockRecordId id);
    StockRecordDto createStockRecord(StockRecordCreateDto stockRecordDto);
    StockRecordDto updateStockRecord(StockRecordId stockRecordId, StockRecordUpdateDto stockRecordDto);
    void deleteStockRecord(StockRecordId id);
}
