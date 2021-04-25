package exception;

/**
 * Исключение, которое выбрасывается файл не был найден
 */
public class FileWasNotFoundException extends AbstractException {

    public FileWasNotFoundException(String message) {
        super(message);
    }

}
