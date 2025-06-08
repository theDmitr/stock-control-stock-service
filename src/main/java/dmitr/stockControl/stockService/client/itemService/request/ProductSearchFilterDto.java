package dmitr.stockControl.stockService.client.itemService.request;

import lombok.Builder;
import lombok.Getter;

import java.util.Collection;
import java.util.UUID;

@Getter
@Builder
public class ProductSearchFilterDto {

    private Collection<UUID> productIds;
}
