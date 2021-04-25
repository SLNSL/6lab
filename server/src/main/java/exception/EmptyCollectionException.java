package exception;

/**
 * Исключение, которое выбрасывается, если коллекция пуста
 */
public class EmptyCollectionException extends AbstractException {

    public EmptyCollectionException(String message) {
        super(message);
    }

}
