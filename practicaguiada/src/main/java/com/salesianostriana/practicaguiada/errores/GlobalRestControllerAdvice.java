package com.salesianostriana.practicaguiada.errores;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalRestControllerAdvice extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildApiError(ex,request,ex.getFieldErrors().stream().map(
                error -> ValidationError.builder()
                        .objeto(error.getObjectName())
                        .campo(error.getField())
                        .mensaje(error.getDefaultMessage())
                        .ValorRechazado(error.getRejectedValue())
                        .build()
        ).collect(Collectors.toList()));
    }

    private ResponseEntity<Object> buildApiError(Exception ex, WebRequest request, List<ApiSubError> subErrors){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiError(HttpStatus.NOT_FOUND, ex.getMessage(),((ServletWebRequest) request).getRequest().getRequestURI(),subErrors));
    }
    private ResponseEntity<Object> buildApiError(Exception ex, WebRequest request){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiError(HttpStatus.NOT_FOUND, ex.getMessage()));
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiError apiError = ApiError.builder()
                .status(status)
                .codigo(status.value())
                .ruta(((ServletWebRequest) request).getRequest().getRequestURI())
                .mensaje(ex.getMessage())
                .build();
        return ResponseEntity.status(status).body(apiError);
    }
    @ExceptionHandler(EstacionNotFoundException.class)
    public ResponseEntity<ApiError> handleEstacionNotFound (EstacionNotFoundException ex){
        ApiError apiError = ApiError.builder()
                .mensaje(ex.getMessage())
                .status(HttpStatus.NOT_FOUND)
                .codigo(HttpStatus.NOT_FOUND.value())
                .fecha(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }
    @ExceptionHandler(ListEntityNotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(ListEntityNotFoundException ex){
        ApiError apiError = ApiError.builder()
                .mensaje(ex.getMessage())
                .status(HttpStatus.NOT_FOUND)
                .codigo(HttpStatus.NOT_FOUND.value())
                .fecha(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }
}
