package exception;

/**
 * Исключение, которое выбрасывается, если ввод полей был неправильным
 */
public class IncorrectFieldInDataException extends AbstractException {

    public IncorrectFieldInDataException(String message) {
        super(message);
    }

}
