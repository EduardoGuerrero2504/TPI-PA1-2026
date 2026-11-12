package ar.edu.undec.adapter.handler;

import empleado.exception.EmpleadoDuplicadoException;
import empleado.exception.EmpleadoInvalidaException;
import empleado.exception.EmpleadoLegajoNullException;
import empleado.exception.EmpleadoNoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({EmpleadoInvalidaException.class, EmpleadoLegajoNullException.class})
    public ResponseEntity<?> empleadoInvalido(Exception exception) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(exception.getMessage());
    }

    @ExceptionHandler(EmpleadoDuplicadoException.class)
    public ResponseEntity<String> empleadoDuplicado(EmpleadoDuplicadoException exception) {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(exception.getMessage());
    }

    @ExceptionHandler(EmpleadoNoEncontradoException.class)
    public ResponseEntity<String> empleadoNoEncontrado(EmpleadoNoEncontradoException exception) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }
}
