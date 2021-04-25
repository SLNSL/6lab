package exception;


/**
 * Исключение, которое выбрасывается, если количество аргументов было неверным
 */

public class IncorrectNumberOfArgumentsException extends AbstractException {

    public IncorrectNumberOfArgumentsException(String message) {
        super(message);
    }

}
