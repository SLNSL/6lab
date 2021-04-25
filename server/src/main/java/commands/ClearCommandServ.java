package commands;

import exception.IncorrectNumberOfArgumentsException;
import messenger.Messenger;
import pattern.Collection;
import wrappers.FieldResult;
import wrappers.Result;

public class ClearCommandServ implements Command {
    private Collection collectionManager;
    private Messenger messenger;


    public ClearCommandServ(Collection collectionManager, Messenger messenger) {
        this.collectionManager = collectionManager;
        this.messenger = messenger;
    }


    @Override
    public Result<String> execute(int port, Object... args) throws IncorrectNumberOfArgumentsException {
        collectionManager.setMessenger(messenger);
        collectionManager.clear();

        return new FieldResult<>(messenger.commandIsFinished("clear"));
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
