package commands;

import data.Product;
import exception.IncorrectNumberOfArgumentsException;
import messenger.Messenger;
import pattern.Collection;
import wrappers.FieldResult;
import wrappers.Result;

public class RemoveLowerCommandServ implements Command {
    private Messenger messenger;
    private Collection collectionManager;

    public RemoveLowerCommandServ(Collection collectionManager, Messenger messenger) {
        this.messenger = messenger;
        this.collectionManager = collectionManager;
    }

    @Override
    public Result<String> execute(int port, Object... args) throws IncorrectNumberOfArgumentsException {

        Product product = (Product) args[0];

        collectionManager.setMessenger(messenger);
        collectionManager.removeLower(product);

        return new FieldResult<>(messenger.commandIsFinished("remove_lower"));

    }

    @Override
    public void setMessenger(Messenger messenger) {

    }

    @Override
    public Messenger getMessenger() {
        return null;
    }
}
