package commands;

import exception.EmptyCollectionException;
import exception.IncorrectNumberOfArgumentsException;
import messenger.Messenger;
import pattern.Collection;
import wrappers.FieldResult;
import wrappers.Result;

public class ShowCommandServ implements Command {
    private Collection collectionManager;
    private Messenger messenger;


    public ShowCommandServ(Collection collectionManager, Messenger messenger) {
        this.collectionManager = collectionManager;
        this.messenger = messenger;
    }


    @Override
    public Result<String> execute(int port, Object... args) throws IncorrectNumberOfArgumentsException {
        collectionManager.setMessenger(messenger);

        try {
            String collectionInfo = collectionManager.writeCollection();
            return new FieldResult<>(collectionInfo + "\n" + messenger.commandIsFinished("show") + "\n");
        } catch (EmptyCollectionException e) {
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
