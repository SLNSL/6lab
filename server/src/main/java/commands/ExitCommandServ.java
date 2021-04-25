package commands;

import exception.IncorrectNumberOfArgumentsException;
import messenger.Messenger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pattern.Collection;
import wrappers.FieldResult;
import wrappers.Result;

public class ExitCommandServ implements Command {

    public static final Logger logger = LoggerFactory.getLogger("Server");

    private Messenger messenger;
    private Collection collectionManager;

    public ExitCommandServ(Collection collectionManager, Messenger messenger) {
        this.messenger = messenger;
        this.collectionManager = collectionManager;
    }

    /**
     * Выполнение команды
     *
     * @return - true - если команда работала без ошибок, false - если команда работала с ошибками
     * @throws IncorrectNumberOfArgumentsException - было передано недопустимое количество аргументов
     */
    @Override
    public Result<String> execute(int port, Object... args) {

        Command saveCommand = new SaveCommandServ(collectionManager);
        Result<String> result = saveCommand.execute(0, 0);
        if (result.hasError()) {
            logger.error(result.getError());
        } else {
            logger.info(result.getResult());
        }

        return new FieldResult<>(messenger.commandIsFinished("exit"));
    }

    @Override
    public void setMessenger(Messenger messenger) {
        this.messenger = messenger;

    }

    @Override
    public Messenger getMessenger() {
        return messenger;
    }
}
