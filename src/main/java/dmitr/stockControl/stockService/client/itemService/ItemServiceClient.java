package dmitr.stockControl.stockService.client.itemService;

import dmitr.stockControl.stockService.client.itemService.request.ProductSearchFilterDto;
import dmitr.stockControl.stockService.client.itemService.response.ProductStockResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "item-service", configuration = ItemServiceConfiguration.class)
public interface ItemServiceClient {

    @GetMapping("/products/{productId}")
    Object getProduct(@PathVariable UUID productId);

    @PostMapping("/products/search/stock")
    List<ProductStockResponseDto> getProductsToStockByFilter(ProductSearchFilterDto filter);
}
