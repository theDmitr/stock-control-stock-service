package dmitr.stockControl.stockService.helper.mapper.stockRecord;

import dmitr.stockControl.stockService.dao.entity.stockRecord.StockRecord;
import dmitr.stockControl.stockService.model.stockRecord.StockRecordCreateDto;
import dmitr.stockControl.stockService.model.stockRecord.StockRecordDto;
import dmitr.stockControl.stockService.model.stockRecord.StockRecordUpdateDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StockRecordMapper {

    StockRecordDto toDto(StockRecord stockRecord);
    List<StockRecordDto> toDto(List<StockRecord> stockRecords);
    StockRecord fromDto(StockRecordCreateDto stockRecordDto);
    StockRecord fromDto(StockRecordUpdateDto stockRecordDto);
}
