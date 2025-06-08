package dmitr.stockControl.stockService.model.stockHolder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class StockHolderDto {

    private UUID id;
    private String name;
    private String description;
    private String image;
}
