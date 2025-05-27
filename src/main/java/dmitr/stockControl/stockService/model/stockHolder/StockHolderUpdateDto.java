package dmitr.stockControl.stockService.model.stockHolder;

import dmitr.stockControl.stockService.model.stockHolder.face.StockHolderBaseValidation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class StockHolderUpdateDto implements StockHolderBaseValidation {

    private UUID id;
    private String name;
}
