package dmitr.stockControl.stockService.controller.stockRecord;

import dmitr.stockControl.stockService.controller.stockRecord.response.StockRecordListViewResponseDto;
import dmitr.stockControl.stockService.dao.entity.stockRecord.StockRecordId;
import dmitr.stockControl.stockService.model.stockRecord.StockRecordCreateDto;
import dmitr.stockControl.stockService.model.stockRecord.StockRecordDto;
import dmitr.stockControl.stockService.model.stockRecord.StockRecordUpdateDto;
import dmitr.stockControl.stockService.service.face.stockRecord.StockRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/stock-records")
@RequiredArgsConstructor
public class StockRecordRestController {

    private final StockRecordService stockRecordService;

    @GetMapping
    public List<StockRecordDto> getStockRecords() {
        return stockRecordService.getStockRecords();
    }

    @GetMapping("/stockHolder/{stockHolderId}/product/{productId}")
    public StockRecordDto getStockRecord(@PathVariable UUID stockHolderId,
                                         @PathVariable UUID productId) {
        return stockRecordService.getStockRecord(new StockRecordId(stockHolderId, productId));
    }

    @GetMapping("/list/page-view")
    public List<StockRecordListViewResponseDto> getStockRecordsListView() {
        return stockRecordService.getStockHolderListView();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public StockRecordDto createStockRecord(@RequestBody StockRecordCreateDto stockRecordDto) {
        return stockRecordService.createStockRecord(stockRecordDto);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PatchMapping("/stockHolder/{stockHolderId}/product/{productId}")
    public StockRecordDto updateStockRecord(@PathVariable UUID stockHolderId,
                                            @PathVariable UUID productId,
                                            @RequestBody StockRecordUpdateDto stockRecordDto) {
        return stockRecordService.updateStockRecord(new StockRecordId(stockHolderId, productId), stockRecordDto);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/stockHolder/{stockHolderId}/product/{productId}")
    public void deleteStockRecord(@PathVariable UUID stockHolderId,
                                  @PathVariable UUID productId) {
        stockRecordService.deleteStockRecord(new StockRecordId(stockHolderId, productId));
    }
}
