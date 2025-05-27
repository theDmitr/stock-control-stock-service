package dmitr.stockControl.stockService.client.itemService;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "item-service", configuration = ItemServiceConfiguration.class)
public interface ItemServiceClient {

    @GetMapping("/products/{productId}")
    Object getProduct(@PathVariable UUID productId);
}
