package dmitr.stockControl.stockService.exception.handler;

import dmitr.stockControl.stockService.exception.base.BadRequestException;
import dmitr.stockControl.stockService.exception.base.NotFoundException;
import dmitr.stockControl.stockService.exception.base.UnauthorizedException;
import dmitr.stockControl.stockService.exception.handler.response.CommonErrorResponse;
import dmitr.stockControl.stockService.helper.exception.HttpExceptionHelper;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.ByteBuffer;
import java.util.Optional;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class CommonExceptionHandler {

    private final HttpExceptionHelper httpExceptionHelper;

    @ExceptionHandler
    public ResponseEntity<CommonErrorResponse> handle(NotFoundException e) {
        return httpExceptionHelper.generateErrorResponseEntity(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<CommonErrorResponse> handle(BadRequestException e) {
        return httpExceptionHelper.generateErrorResponseEntity(e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<CommonErrorResponse> handle(UnauthorizedException e) {
        return httpExceptionHelper.generateErrorResponseEntity(e, HttpStatus.UNAUTHORIZED);
    }

    /**
     * Метод обрабатывает исключения, вызванные внешними сервисами через Feign,
     * перенаправляя клиенту тот же самый ответ, полученный от удаленного сервиса.
     */
    @ExceptionHandler
    public ResponseEntity<CommonErrorResponse> handleFeignException(FeignException e) {
        log.error("Получено исключение из внешнего сервиса с кодом {}", e.status());

        String responseBodyMessage = "";

        Optional<ByteBuffer> responseBodyBytes = e.responseBody();
        if (responseBodyBytes.isPresent()) {
            byte[] responseBody = responseBodyBytes.get().array();
            responseBodyMessage = new String(responseBody);
        }

        CommonErrorResponse errorResponse = CommonErrorResponse.builder()
                .message(responseBodyMessage)
                .code(e.status())
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(e.status()));
    }
}
