package commands;


import exception.IncorrectNumberOfArgumentsException;
import exception.InputOutputException;
import messenger.Messenger;
import pattern.Collection;
import wrappers.FieldResult;
import wrappers.Result;


/**
 * Команда которая сохраняет коллекцию в файл
 */
public class SaveCommandServ implements Command {
    private Collection collectionManager;
    private Messenger messenger;

    public SaveCommandServ(Collection collectionManager) {
        this.collectionManager = collectionManager;
    }

    /**
     * Выполнение команды
     *
     * @param argument - аргумент, который может быть передан или не передан вместе с командой
     * @return - true - если команда работала без ошибок, false - если команда работала с ошибками
     * @throws IncorrectNumberOfArgumentsException - было передано недопустимое количество аргументов
     */
    @Override
    public Result<String> execute(int port, Object... argument) throws IncorrectNumberOfArgumentsException {
        collectionManager.setMessenger(messenger);
        collectionManager.sort();
        try {
            collectionManager.save();
            return new FieldResult<>("Коллекция была успешно сохранена." + "\n");
        } catch (InputOutputException e) {
            return new FieldResult<>(e.getMessage() + "\n");
        }
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