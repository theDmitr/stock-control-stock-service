package dmitr.stockControl.stockService.controller.stockRecord.response;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class StockRecordListViewResponseDto {

    private UUID stockHolderId;
    private String stockHolderName;
    private String stockHolderImage;
    private Long quantity;
    private UUID productId;
    private String productName;
    private String productImage;
}
