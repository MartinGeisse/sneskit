package name.martingeisse.sneskit.deconstruct;

public class DeconstructException extends RuntimeException {

    public DeconstructException() {
    }

    public DeconstructException(String message) {
        super(message);
    }

    public DeconstructException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeconstructException(Throwable cause) {
        super(cause);
    }

}
