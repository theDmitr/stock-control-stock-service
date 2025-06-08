package dmitr.stockControl.stockService.service.face.stockHolder;

import dmitr.stockControl.stockService.controller.stockHolder.response.StockHolderInfoResponseDto;
import dmitr.stockControl.stockService.controller.stockHolder.response.StockHolderListViewResponseDto;
import dmitr.stockControl.stockService.model.stockHolder.StockHolderCreateDto;
import dmitr.stockControl.stockService.model.stockHolder.StockHolderDto;
import dmitr.stockControl.stockService.model.stockHolder.StockHolderUpdateDto;

import java.util.List;
import java.util.UUID;

public interface StockHolderService {

    List<StockHolderDto> getStockHolders();
    StockHolderDto getStockHolder(UUID id);
    List<StockHolderListViewResponseDto> getStockHolderListView();
    StockHolderInfoResponseDto getStockHolderInfo(UUID stockHolderId);
    StockHolderDto createStockHolder(StockHolderCreateDto stockHolderDto);
    StockHolderDto updateStockHolder(UUID stockHolderId, StockHolderUpdateDto stockHolderDto);
    void deleteStockHolder(UUID id);
}
