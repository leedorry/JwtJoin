package io.github.jhlee.response;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleValidationException(MethodArgumentNotValidException ex) {

        Map<String, String> errorMap = ex.getBindingResult()
                        .getFieldErrors()
                        .stream()
                        .collect(Collectors.toMap(
                            FieldError::getField,
                            error-> Optional.ofNullable(error.getDefaultMessage()).orElse("유효하지 않은 값입니다."),
                            (existing, replacement) -> existing
                        ));

        return new ResponseEntity<>(
                ApiResponse.error(Status.BAD_REQUEST, errorMap),
                Status.BAD_REQUEST.getHttpStatus()
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<?>> handleIllegalArgumentException(IllegalArgumentException ex) {

        return new ResponseEntity<>(
                ApiResponse.error(Status.BAD_REQUEST, Map.of("message", ex.getMessage())),
                Status.BAD_REQUEST.getHttpStatus()
        );

    }

}
