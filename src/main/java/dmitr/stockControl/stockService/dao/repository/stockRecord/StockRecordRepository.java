package dmitr.stockControl.stockService.dao.repository.stockRecord;

import dmitr.stockControl.stockService.dao.entity.stockRecord.StockRecord;
import dmitr.stockControl.stockService.dao.entity.stockRecord.StockRecordId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StockRecordRepository extends JpaRepository<StockRecord, StockRecordId> {

    @Query("""
            from StockRecord sr
            where sr.id.stockHolderId = :stockHolderId
    """)
    List<StockRecord> findByStockHolderId(UUID stockHolderId);
}
