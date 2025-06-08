package dmitr.stockControl.stockService.client.itemService.response;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class ProductStockResponseDto {

    private UUID id;
    private String name;
    private String image;
}
