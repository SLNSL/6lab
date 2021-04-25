package exception;

/**
 * Исключение, которое выбрасывается, если файл с вашей коллекцией пуст
 */
public class EmptyFileException extends AbstractException {

    public EmptyFileException(String message) {
        super(message);
    }

}
