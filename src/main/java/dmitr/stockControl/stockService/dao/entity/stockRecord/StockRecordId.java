package dmitr.stockControl.stockService.dao.entity.stockRecord;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class StockRecordId implements Serializable {

    @Column(name = "stock_holder_id", nullable = false)
    private UUID stockHolderId;

    @Column(name = "product_id", nullable = false)
    private UUID productId;
}
