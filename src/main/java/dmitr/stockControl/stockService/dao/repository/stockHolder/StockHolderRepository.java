package dmitr.stockControl.stockService.dao.repository.stockHolder;

import dmitr.stockControl.stockService.dao.entity.stockHolder.StockHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StockHolderRepository extends JpaRepository<StockHolder, UUID> {
}
