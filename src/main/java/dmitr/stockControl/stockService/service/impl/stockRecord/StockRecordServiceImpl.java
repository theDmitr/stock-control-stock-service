package dmitr.stockControl.stockService.service.impl.stockRecord;

import dmitr.stockControl.stockService.client.itemService.ItemServiceClient;
import dmitr.stockControl.stockService.client.itemService.request.ProductSearchFilterDto;
import dmitr.stockControl.stockService.client.itemService.response.ProductStockResponseDto;
import dmitr.stockControl.stockService.controller.stockHolder.response.ProductStockRecordResponseDto;
import dmitr.stockControl.stockService.controller.stockRecord.response.StockRecordListViewResponseDto;
import dmitr.stockControl.stockService.dao.entity.stockHolder.StockHolder;
import dmitr.stockControl.stockService.dao.entity.stockRecord.StockRecord;
import dmitr.stockControl.stockService.dao.entity.stockRecord.StockRecordId;
import dmitr.stockControl.stockService.dao.repository.stockHolder.StockHolderRepository;
import dmitr.stockControl.stockService.dao.repository.stockRecord.StockRecordRepository;
import dmitr.stockControl.stockService.exception.extended.ValidationException;
import dmitr.stockControl.stockService.exception.extended.stockHolder.NotFoundStockHolderException;
import dmitr.stockControl.stockService.exception.extended.stockRecord.NotFoundStockRecordException;
import dmitr.stockControl.stockService.helper.mapper.stockRecord.StockRecordMapper;
import dmitr.stockControl.stockService.model.stockRecord.StockRecordCreateDto;
import dmitr.stockControl.stockService.model.stockRecord.StockRecordDto;
import dmitr.stockControl.stockService.model.stockRecord.StockRecordUpdateDto;
import dmitr.stockControl.stockService.model.stockRecord.face.StockRecordBaseValidation;
import dmitr.stockControl.stockService.service.face.stockRecord.StockRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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
    public List<StockRecordListViewResponseDto> getStockHolderListView() {
        List<StockRecord> stockRecords = stockRecordRepository.findAll();

        Map<UUID, StockHolder> stockHoldersMap = stockHolderRepository.findAll()
                .stream()
                .collect(Collectors.toMap(StockHolder::getId, stockHolder -> stockHolder));

        Set<UUID> productsIds = stockRecords.stream()
                .map(sr -> sr.getId().getProductId())
                .collect(Collectors.toSet());
        Map<UUID, ProductStockResponseDto> productsToStockByIdMap = getProductsToStock(productsIds)
                .stream()
                .collect(Collectors.toMap(ProductStockResponseDto::getId, p -> p));

        return stockRecords.stream()
                .map(stockRecord -> {
                    StockHolder stockHolder = stockHoldersMap.get(stockRecord.getId().getStockHolderId());

                    ProductStockResponseDto product = productsToStockByIdMap.get(stockRecord.getId().getProductId());

                    if (stockHolder == null || product == null) {
                        return null;
                    }

                    return StockRecordListViewResponseDto.builder()
                            .stockHolderId(stockHolder.getId())
                            .stockHolderName(stockHolder.getName())
                            .stockHolderImage(stockHolder.getImage())
                            .quantity(stockRecord.getQuantity())
                            .productId(product.getId())
                            .productName(product.getName())
                            .productImage(product.getImage())
                            .build();
                })
                .filter(Objects::nonNull)
                .toList();
    }

    @Override
    public List<ProductStockRecordResponseDto> getHolderStockRecords(UUID stockHolderId) {
        stockHolderRepository.findById(stockHolderId)
                .orElseThrow(NotFoundStockHolderException::new);

        Map<UUID, StockRecord> stockRecordByIdMap = stockRecordRepository.findByStockHolderId(stockHolderId)
                .stream()
                .collect(Collectors.toMap(sr -> sr.getId().getProductId(), sr -> sr));

        Set<UUID> productsIds = stockRecordByIdMap.keySet();
        List<ProductStockResponseDto> productsToStock = getProductsToStock(productsIds);

        return productsToStock.stream()
                .map(productToStock -> {
                    StockRecord stockRecord = stockRecordByIdMap.get(productToStock.getId());
                    Long quantity = stockRecord != null ? stockRecord.getQuantity() : 0L;
                    return ProductStockRecordResponseDto.builder()
                            .stockHolderId(stockHolderId)
                            .productId(productToStock.getId())
                            .name(productToStock.getName())
                            .image(productToStock.getImage())
                            .quantity(quantity)
                            .build();
                })
                .toList();
    }

    private List<ProductStockResponseDto> getProductsToStock(Collection<UUID> productsIds) {
        ProductSearchFilterDto filter = ProductSearchFilterDto.builder()
                .productIds(productsIds)
                .build();
        return itemServiceClient.getProductsToStockByFilter(filter);
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
        Long quantity = stockRecord.getQuantity();
        if (quantity == null) {
            throw new ValidationException("stockRecord.validation.quantity.required");
        }
        if (quantity <= 0) {
            throw new ValidationException("stockRecord.validation.quantity.invalid");
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
