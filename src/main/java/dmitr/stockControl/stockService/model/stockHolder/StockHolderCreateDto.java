package dmitr.stockControl.stockService.model.stockHolder;

import dmitr.stockControl.stockService.model.stockHolder.face.StockHolderBaseValidation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StockHolderCreateDto implements StockHolderBaseValidation {

    private String name;
    private String description;
    private String image;
}
