package exception;


/**
 * Исключение, которое выбрасывается, если это было исключение ввода-вывода
 */

public class InputOutputException extends AbstractException {

    public InputOutputException(String message) {
        super(message);
    }


}
