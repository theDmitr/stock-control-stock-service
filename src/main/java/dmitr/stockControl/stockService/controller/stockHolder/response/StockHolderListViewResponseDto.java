package dmitr.stockControl.stockService.controller.stockHolder.response;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class StockHolderListViewResponseDto {

    private UUID stockHolderId;
    private String stockHolderName;
    private String stockHolderImage;
    private Long stockRecordsQuantity;
}
