package exception;

/**
 * Исключение, которое выбрасывается, если это была ошибка в скрипте
 */
public class ErrorInScriptException extends AbstractException {

    public ErrorInScriptException(String message) {
        super(message);
    }

}
