package com.salesianostriana.practicaguiada.errores;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class ApiError {

    private HttpStatus status;

    private int codigo;

    private String mensaje;

    private String ruta;

    @Builder.Default
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss")
    private LocalDateTime fecha = LocalDateTime.now();

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<ApiSubError> subErrors;

    public ApiError(HttpStatus status, int codigo, String mensaje, String ruta, LocalDateTime fecha) {
        this.status = status;
        this.codigo = codigo;
        this.mensaje = mensaje;
        this.ruta = ruta;
        this.fecha = fecha;
    }

    public ApiError(HttpStatus status, String mensaje, String ruta, List<ApiSubError> subErrors) {
        this.status = status;
        this.mensaje = mensaje;
        this.ruta = ruta;
        this.subErrors = subErrors;
    }

    public ApiError(HttpStatus notFound, String message) {
        this.status = notFound;
        this.mensaje = message;
    }
}
