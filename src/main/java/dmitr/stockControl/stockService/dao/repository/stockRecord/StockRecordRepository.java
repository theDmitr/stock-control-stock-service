package dmitr.stockControl.stockService.dao.repository.stockRecord;

import dmitr.stockControl.stockService.dao.entity.stockRecord.StockRecord;
import dmitr.stockControl.stockService.dao.entity.stockRecord.StockRecordId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRecordRepository extends JpaRepository<StockRecord, StockRecordId> {
}
