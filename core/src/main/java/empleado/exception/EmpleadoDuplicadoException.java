package empleado.exception;

public class EmpleadoDuplicadoException extends RuntimeException{
    public EmpleadoDuplicadoException(String mensaje) {
        super(mensaje);
    }
}
