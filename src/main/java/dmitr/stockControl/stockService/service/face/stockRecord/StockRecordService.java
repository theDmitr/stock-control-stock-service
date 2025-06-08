package dmitr.stockControl.stockService.service.face.stockRecord;

import dmitr.stockControl.stockService.controller.stockHolder.response.ProductStockRecordResponseDto;
import dmitr.stockControl.stockService.controller.stockRecord.response.StockRecordListViewResponseDto;
import dmitr.stockControl.stockService.dao.entity.stockRecord.StockRecordId;
import dmitr.stockControl.stockService.model.stockRecord.StockRecordCreateDto;
import dmitr.stockControl.stockService.model.stockRecord.StockRecordDto;
import dmitr.stockControl.stockService.model.stockRecord.StockRecordUpdateDto;

import java.util.List;
import java.util.UUID;

public interface StockRecordService {

    List<StockRecordDto> getStockRecords();
    StockRecordDto getStockRecord(StockRecordId id);
    List<StockRecordListViewResponseDto> getStockHolderListView();
    List<ProductStockRecordResponseDto> getHolderStockRecords(UUID stockHolderId);
    StockRecordDto createStockRecord(StockRecordCreateDto stockRecordDto);
    StockRecordDto updateStockRecord(StockRecordId stockRecordId, StockRecordUpdateDto stockRecordDto);
    void deleteStockRecord(StockRecordId id);
}
