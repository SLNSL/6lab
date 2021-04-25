package exception;


/**
 * Исключение, которое выбрасывается, если это синтаксическая ошибка json
 */

public class SyntaxJsonException extends AbstractException {

    public SyntaxJsonException(String message) {
        super(message);
    }

}
