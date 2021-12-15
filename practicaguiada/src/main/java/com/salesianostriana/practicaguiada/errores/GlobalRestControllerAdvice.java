package com.salesianostriana.practicaguiada.errores;

import com.salesianostriana.practicaguiada.errores.excepciones.EstacionNotFoundException;
import com.salesianostriana.practicaguiada.errores.excepciones.ListEntityNotFoundException;
import com.salesianostriana.practicaguiada.errores.model.ApiError;
import com.salesianostriana.practicaguiada.errores.model.ApiSubError;
import com.salesianostriana.practicaguiada.errores.model.ValidationError;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalRestControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<?> handleConstraintViolationException (ConstraintViolationException ex, WebRequest request){
        return buildApiError(HttpStatus.BAD_REQUEST,ex,request,
                ex.getConstraintViolations().stream().map(error ->
                ValidationError.builder()
                        .objeto(error.getRootBeanClass().getSimpleName())
                        .campo(((PathImpl)error.getPropertyPath()).getLeafNode().asString())
                        .ValorRechazado(error.getInvalidValue())
                        .mensaje(error.getMessage())
                        .build()).collect(Collectors.toList())
        );
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<ApiSubError> subErrorList = new ArrayList<>();

        ex.getAllErrors().forEach(error -> {
            if(error instanceof FieldError){
                FieldError fieldError = (FieldError) error;

                subErrorList.add(ValidationError.builder()
                                .objeto(fieldError.getObjectName())
                                .campo(fieldError.getField())
                                .mensaje(fieldError.getDefaultMessage())
                                .ValorRechazado(fieldError.getRejectedValue())
                        .build());
            }
            else{
                ObjectError objectError = error;

                subErrorList.add(ValidationError.builder()
                                .objeto(objectError.getObjectName())
                                .mensaje(objectError.getDefaultMessage())
                        .build());
            }
        });

        return buildApiError(HttpStatus.BAD_REQUEST,ex,request,subErrorList.isEmpty() ? null : subErrorList);
    }

    private ResponseEntity<Object> buildApiError( HttpStatus status, Exception ex, WebRequest request, List<ApiSubError> subErrors){
        return ResponseEntity.status(status)
                .body(new ApiError(status,status.value(), ex.getMessage(),((ServletWebRequest) request).getRequest().getRequestURI(),subErrors,LocalDateTime.now()));
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
