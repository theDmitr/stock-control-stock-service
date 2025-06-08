package dmitr.stockControl.stockService.service.impl.stockHolder;

import dmitr.stockControl.stockService.controller.stockHolder.response.ProductStockRecordResponseDto;
import dmitr.stockControl.stockService.controller.stockHolder.response.StockHolderInfoResponseDto;
import dmitr.stockControl.stockService.controller.stockHolder.response.StockHolderListViewResponseDto;
import dmitr.stockControl.stockService.dao.entity.stockHolder.StockHolder;
import dmitr.stockControl.stockService.dao.entity.stockRecord.StockRecord;
import dmitr.stockControl.stockService.dao.repository.stockHolder.StockHolderRepository;
import dmitr.stockControl.stockService.dao.repository.stockRecord.StockRecordRepository;
import dmitr.stockControl.stockService.exception.extended.ValidationException;
import dmitr.stockControl.stockService.exception.extended.stockHolder.NotFoundStockHolderException;
import dmitr.stockControl.stockService.helper.mapper.stockHolder.StockHolderMapper;
import dmitr.stockControl.stockService.model.stockHolder.StockHolderCreateDto;
import dmitr.stockControl.stockService.model.stockHolder.StockHolderDto;
import dmitr.stockControl.stockService.model.stockHolder.StockHolderUpdateDto;
import dmitr.stockControl.stockService.model.stockHolder.face.StockHolderBaseValidation;
import dmitr.stockControl.stockService.service.face.stockHolder.StockHolderService;
import dmitr.stockControl.stockService.service.face.stockRecord.StockRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import static io.micrometer.common.util.StringUtils.isBlank;

@Service
@RequiredArgsConstructor
public class StockHolderServiceImpl implements StockHolderService {

    private final StockHolderRepository stockHolderRepository;
    private final StockRecordRepository stockRecordRepository;

    private final StockRecordService stockRecordService;

    private final StockHolderMapper stockHolderMapper;

    @Override
    public List<StockHolderDto> getStockHolders() {
        List<StockHolder> stockHolders = stockHolderRepository.findAll();
        return stockHolderMapper.toDto(stockHolders);
    }

    @Override
    public List<StockHolderListViewResponseDto> getStockHolderListView() {
        List<StockHolder> stockHolders = stockHolderRepository.findAll();

        Map<UUID, List<StockRecord>> stockRecordsMap = stockRecordRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(stockRecord -> stockRecord.getId().getStockHolderId()));

        return stockHolders.stream()
                .map(stockHolder -> {
                    List<StockRecord> stockRecords = stockRecordsMap.get(stockHolder.getId());
                    Long quantity = stockRecords != null ? (long) stockRecords.size() : 0L;
                    return StockHolderListViewResponseDto.builder()
                            .stockHolderName(stockHolder.getName())
                            .stockHolderImage(stockHolder.getImage())
                            .stockHolderId(stockHolder.getId())
                            .stockRecordsQuantity(quantity)
                            .build();
                })
                .toList();
    }

    @Override
    public StockHolderInfoResponseDto getStockHolderInfo(UUID stockHolderId) {
        StockHolder stockHolder = stockHolderRepository.findById(stockHolderId)
                .orElseThrow(NotFoundStockHolderException::new);

        List<ProductStockRecordResponseDto> productStockRecords = stockRecordService.getHolderStockRecords(stockHolder.getId());

        Long quantity = productStockRecords.stream()
                .mapToLong(ProductStockRecordResponseDto::getQuantity)
                .sum();

        return StockHolderInfoResponseDto.builder()
                .stockHolderName(stockHolder.getName())
                .stockHolderImage(stockHolder.getImage())
                .stockHolderId(stockHolder.getId())
                .stockRecordsQuantity(quantity)
                .stockRecords(productStockRecords)
                .build();
    }

    @Override
    public StockHolderDto getStockHolder(UUID id) {
        StockHolder stockHolder = stockHolderRepository.findById(id)
                .orElseThrow(NotFoundStockHolderException::new);
        return stockHolderMapper.toDto(stockHolder);
    }

    @Override
    public StockHolderDto createStockHolder(StockHolderCreateDto stockHolderDto) {
        baseValidation(stockHolderDto);

        StockHolder category = stockHolderMapper.fromDto(stockHolderDto);
        stockHolderRepository.save(category);

        return stockHolderMapper.toDto(category);
    }

    @Override
    public StockHolderDto updateStockHolder(UUID stockHolderId, StockHolderUpdateDto stockHolderDto) {
        stockHolderRepository.findById(stockHolderId)
                .orElseThrow(NotFoundStockHolderException::new);

        baseValidation(stockHolderDto);

        StockHolder category = stockHolderMapper.fromDto(stockHolderDto);
        category.setId(stockHolderId);
        stockHolderRepository.save(category);

        return stockHolderMapper.toDto(category);
    }

    private void baseValidation(StockHolderBaseValidation stockHolder) {
        String name = stockHolder.getName();
        if (isBlank(name)) {
            throw new ValidationException("stockHolder.validation.name.required");
        }
    }

    @Override
    public void deleteStockHolder(UUID id) {
        stockHolderRepository.deleteById(id);
    }
}
