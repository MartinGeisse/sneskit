package name.martingeisse.sneskit.util;

public class KitException extends RuntimeException {

    public KitException() {
    }

    public KitException(String message) {
        super(message);
    }

    public KitException(String message, Throwable cause) {
        super(message, cause);
    }

    public KitException(Throwable cause) {
        super(cause);
    }

}
