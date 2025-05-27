package dmitr.stockControl.stockService.model.stockRecord;

import dmitr.stockControl.stockService.dao.entity.stockRecord.StockRecordId;
import dmitr.stockControl.stockService.model.stockRecord.face.StockRecordBaseValidation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StockRecordCreateDto implements StockRecordBaseValidation {

    private StockRecordId id;
    private Long count;
}
