package dmitr.stockControl.stockService.service.impl.stockHolder;

import dmitr.stockControl.stockService.dao.entity.stockHolder.StockHolder;
import dmitr.stockControl.stockService.dao.repository.stockHolder.StockHolderRepository;
import dmitr.stockControl.stockService.exception.extended.ValidationException;
import dmitr.stockControl.stockService.exception.extended.stockHolder.NotFoundStockHolderException;
import dmitr.stockControl.stockService.helper.mapper.stockHolder.StockHolderMapper;
import dmitr.stockControl.stockService.model.stockHolder.StockHolderCreateDto;
import dmitr.stockControl.stockService.model.stockHolder.StockHolderDto;
import dmitr.stockControl.stockService.model.stockHolder.StockHolderUpdateDto;
import dmitr.stockControl.stockService.model.stockHolder.face.StockHolderBaseValidation;
import dmitr.stockControl.stockService.service.face.stockHolder.StockHolderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static io.micrometer.common.util.StringUtils.isBlank;

@Service
@RequiredArgsConstructor
public class StockHolderServiceImpl implements StockHolderService {

    private final StockHolderRepository stockHolderRepository;

    private final StockHolderMapper stockHolderMapper;

    @Override
    public List<StockHolderDto> getStockHolders() {
        List<StockHolder> stockHolders = stockHolderRepository.findAll();
        return stockHolderMapper.toDto(stockHolders);
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
