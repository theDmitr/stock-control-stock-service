package dmitr.stockControl.stockService.helper.mapper.stockHolder;

import dmitr.stockControl.stockService.dao.entity.stockHolder.StockHolder;
import dmitr.stockControl.stockService.model.stockHolder.StockHolderCreateDto;
import dmitr.stockControl.stockService.model.stockHolder.StockHolderDto;
import dmitr.stockControl.stockService.model.stockHolder.StockHolderUpdateDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StockHolderMapper {

    StockHolderDto toDto(StockHolder stockHolder);
    List<StockHolderDto> toDto(List<StockHolder> stockHolders);
    StockHolder fromDto(StockHolderCreateDto stockHolderDto);
    StockHolder fromDto(StockHolderUpdateDto stockHolderDto);
}
