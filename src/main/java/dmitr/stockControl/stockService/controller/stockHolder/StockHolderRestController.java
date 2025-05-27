package dmitr.stockControl.stockService.controller.stockHolder;

import dmitr.stockControl.stockService.model.stockHolder.StockHolderCreateDto;
import dmitr.stockControl.stockService.model.stockHolder.StockHolderDto;
import dmitr.stockControl.stockService.model.stockHolder.StockHolderUpdateDto;
import dmitr.stockControl.stockService.service.face.stockHolder.StockHolderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/stock_holders")
@RequiredArgsConstructor
public class StockHolderRestController {

    private final StockHolderService stockHolderService;

    @GetMapping
    public List<StockHolderDto> getStockHolders() {
        return stockHolderService.getStockHolders();
    }

    @GetMapping("/{stockHolderId}")
    public StockHolderDto getStockHolder(@PathVariable UUID stockHolderId) {
        return stockHolderService.getStockHolder(stockHolderId);
    }

    @PostMapping
    public StockHolderDto createStockHolder(@RequestBody StockHolderCreateDto stockHolderDto) {
        return stockHolderService.createStockHolder(stockHolderDto);
    }

    @PatchMapping("/{stockHolderId}")
    public StockHolderDto updateStockHolder(@PathVariable UUID stockHolderId,
                                            @RequestBody StockHolderUpdateDto stockHolderDto) {
        return stockHolderService.updateStockHolder(stockHolderId, stockHolderDto);
    }

    @DeleteMapping("/{stockHolderId}")
    public void deleteStockHolder(@PathVariable UUID stockHolderId) {
        stockHolderService.deleteStockHolder(stockHolderId);
    }
}
