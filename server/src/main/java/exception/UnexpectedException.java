package exception;


/**
 * Исключение, которое выбрасывается, если это было неожиданное исключение
 */

public class UnexpectedException extends AbstractException {

    public UnexpectedException(String message) {
        super(message);
    }

}
