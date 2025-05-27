package dmitr.stockControl.stockService.helper.exception;

import dmitr.stockControl.stockService.exception.base.CommonException;
import dmitr.stockControl.stockService.exception.handler.response.CommonErrorResponse;
import dmitr.stockControl.stockService.helper.i18n.I18nMessageProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HttpExceptionHelper {

    private final I18nMessageProvider i18nMessageProvider;

    public ResponseEntity<CommonErrorResponse> generateErrorResponseEntity(CommonException e, HttpStatus status) {
        CommonErrorResponse errorResponse = generateErrorResponse(e, status);
        return new ResponseEntity<>(errorResponse, status);
    }

    public CommonErrorResponse generateErrorResponse(CommonException e, HttpStatus status) {
        String localizedMessage = i18nMessageProvider.getMessage(e);
        return CommonErrorResponse.builder()
                .message(localizedMessage)
                .code(status.value())
                .build();
    }
}
