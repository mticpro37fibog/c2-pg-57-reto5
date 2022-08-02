package comun;

public class RetoException extends Exception {
    public RetoException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }

    public RetoException(String mensaje) {
        super(mensaje);
    }
}
