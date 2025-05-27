package dmitr.stockControl.stockService.model.stockRecord;

import dmitr.stockControl.stockService.dao.entity.stockRecord.StockRecordId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StockRecordDto {

    private StockRecordId id;
    private Long count;
}
