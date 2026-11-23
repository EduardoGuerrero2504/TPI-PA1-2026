package empleado.exception;

public class EmpleadoInvalidaException extends RuntimeException {
    public EmpleadoInvalidaException(String mensaje) {
        super(mensaje);
    }
}
