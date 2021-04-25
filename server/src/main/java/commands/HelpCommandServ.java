package commands;

import exception.IncorrectNumberOfArgumentsException;
import messenger.Messenger;
import wrappers.FieldResult;
import wrappers.Result;

public class HelpCommandServ implements Command {

    private Messenger messenger;

    public HelpCommandServ(Messenger messenger) {
        this.messenger = messenger;
    }

    /**
     * Выполнение команды
     *
     * @return - true - если команда работала без ошибок, false - если команда работала с ошибками
     * @throws IncorrectNumberOfArgumentsException - было передано недопустимое количество аргументов
     */
    @Override
    public Result<String> execute(int port, Object... args) {

        return new FieldResult<>(messenger.getCommandsInfo() + "\n" + messenger.commandIsFinished("help") + "\n");
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
