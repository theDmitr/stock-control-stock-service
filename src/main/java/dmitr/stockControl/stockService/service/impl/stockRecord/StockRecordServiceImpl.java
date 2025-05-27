package dmitr.stockControl.stockService.service.impl.stockRecord;

import dmitr.stockControl.stockService.client.itemService.ItemServiceClient;
import dmitr.stockControl.stockService.dao.entity.stockRecord.StockRecord;
import dmitr.stockControl.stockService.dao.entity.stockRecord.StockRecordId;
import dmitr.stockControl.stockService.dao.repository.stockHolder.StockHolderRepository;
import dmitr.stockControl.stockService.dao.repository.stockRecord.StockRecordRepository;
import dmitr.stockControl.stockService.exception.extended.ValidationException;
import dmitr.stockControl.stockService.exception.extended.stockRecord.NotFoundStockRecordException;
import dmitr.stockControl.stockService.helper.mapper.stockRecord.StockRecordMapper;
import dmitr.stockControl.stockService.model.stockRecord.StockRecordCreateDto;
import dmitr.stockControl.stockService.model.stockRecord.StockRecordDto;
import dmitr.stockControl.stockService.model.stockRecord.StockRecordUpdateDto;
import dmitr.stockControl.stockService.model.stockRecord.face.StockRecordBaseValidation;
import dmitr.stockControl.stockService.service.face.stockRecord.StockRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StockRecordServiceImpl implements StockRecordService {

    private final StockRecordRepository stockRecordRepository;
    private final StockHolderRepository stockHolderRepository;

    private final StockRecordMapper stockRecordMapper;

    private final ItemServiceClient itemServiceClient;

    @Override
    public List<StockRecordDto> getStockRecords() {
        List<StockRecord> stockRecords = stockRecordRepository.findAll();
        return stockRecordMapper.toDto(stockRecords);
    }

    @Override
    public StockRecordDto getStockRecord(StockRecordId id) {
        StockRecord stockRecord = stockRecordRepository.findById(id)
                .orElseThrow(NotFoundStockRecordException::new);
        return stockRecordMapper.toDto(stockRecord);
    }

    @Override
    public StockRecordDto createStockRecord(StockRecordCreateDto stockRecordDto) {
        StockRecordId stockRecordId = stockRecordDto.getId();
        stockRecordIdValidation(stockRecordId);

        baseValidation(stockRecordDto);

        StockRecord stockRecord = stockRecordMapper.fromDto(stockRecordDto);
        stockRecordRepository.save(stockRecord);

        return stockRecordMapper.toDto(stockRecord);
    }

    @Override
    public StockRecordDto updateStockRecord(StockRecordId stockRecordId, StockRecordUpdateDto stockRecordDto) {
        stockRecordIdValidation(stockRecordId);
        baseValidation(stockRecordDto);

        StockRecord stockRecord = stockRecordMapper.fromDto(stockRecordDto);
        stockRecord.setId(stockRecordId);
        stockRecordRepository.save(stockRecord);

        return stockRecordMapper.toDto(stockRecord);
    }

    private void baseValidation(StockRecordBaseValidation stockRecord) {
        Long count = stockRecord.getCount();
        if (count == null) {
            throw new ValidationException("stockRecord.validation.count.required");
        }
        if (count <= 0) {
            throw new ValidationException("stockRecord.validation.count.invalid");
        }
    }

    private void stockRecordIdValidation(StockRecordId stockRecordId) {
        if (stockRecordId == null) {
            throw new ValidationException();
        }

        UUID stockHolderId = stockRecordId.getStockHolderId();
        if (stockHolderId == null) {
            throw new ValidationException("stockRecord.validation.stockHolder.required");
        }

        UUID productId = stockRecordId.getProductId();
        if (productId == null) {
            throw new ValidationException("stockRecord.validation.product.required");
        }

        stockHolderRepository.findById(stockHolderId)
                .orElseThrow(NotFoundStockRecordException::new);

        itemServiceClient.getProduct(productId);
    }

    @Override
    public void deleteStockRecord(StockRecordId id) {
        stockRecordRepository.deleteById(id);
    }
}
