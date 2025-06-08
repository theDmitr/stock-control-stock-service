package dmitr.stockControl.stockService.controller.stockHolder.response;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class ProductStockRecordResponseDto {

    private UUID stockHolderId;
    private UUID productId;
    private String name;
    private String image;
    private Long quantity;
}
