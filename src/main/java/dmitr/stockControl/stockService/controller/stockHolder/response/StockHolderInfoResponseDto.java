package dmitr.stockControl.stockService.controller.stockHolder.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
public class StockHolderInfoResponseDto {

    private UUID stockHolderId;
    private String stockHolderName;
    private String stockHolderImage;
    private Long stockRecordsQuantity;
    private List<ProductStockRecordResponseDto> stockRecords;
}
