package dmitr.stockControl.stockService.dao.entity.stockRecord;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "stock_records")
public class StockRecord {

    @EmbeddedId
    private StockRecordId id;

    @Column(name = "quantity", nullable = false)
    private Long quantity;
}
