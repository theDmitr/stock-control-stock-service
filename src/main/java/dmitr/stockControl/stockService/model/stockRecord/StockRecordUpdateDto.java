package dmitr.stockControl.stockService.model.stockRecord;

import dmitr.stockControl.stockService.model.stockRecord.face.StockRecordBaseValidation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StockRecordUpdateDto implements StockRecordBaseValidation {

    private Long quantity;
}
