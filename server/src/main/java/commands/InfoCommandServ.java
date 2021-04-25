package commands;

import exception.IncorrectNumberOfArgumentsException;
import messenger.Messenger;
import pattern.Collection;
import wrappers.FieldResult;
import wrappers.Result;

public class InfoCommandServ implements Command {
    private Collection collectionManager;
    private Messenger messenger;

    public InfoCommandServ(Collection collectionManager, Messenger messenger) {
        this.messenger = messenger;
        this.collectionManager = collectionManager;
    }


    @Override
    public Result<String> execute(int port, Object... args) throws IncorrectNumberOfArgumentsException {
        collectionManager.setMessenger(messenger);
        String info = collectionManager.info();

        return new FieldResult<>(info + "\n" + messenger.commandIsFinished("info"));
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
