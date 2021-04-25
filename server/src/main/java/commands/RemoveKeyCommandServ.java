package commands;

import exception.IncorrectNumberOfArgumentsException;
import messenger.Messenger;
import checker.ServerDataChecker;
import pattern.Collection;
import wrappers.FieldResult;
import wrappers.Result;

public class RemoveKeyCommandServ implements Command {
    private ServerDataChecker fieldsChecker;
    private Collection collectionManager;
    private Messenger messenger;

    public RemoveKeyCommandServ(Collection collectionManager, Messenger messenger, ServerDataChecker serverDataChecker) {
        this.fieldsChecker = serverDataChecker;
        this.collectionManager = collectionManager;
        this.messenger = messenger;
    }


    @Override
    public Result<String> execute(int port, Object... args) throws IncorrectNumberOfArgumentsException {
        Integer key = (Integer) args[0];

        Result<Object> result = collectionManager.delete(key);
        if (result.hasError()) {
            return new FieldResult<>(result.getError());
        } else {
            return new FieldResult<>(messenger.commandIsFinished("remove_key"));
        }
    }

    @Override
    public void setMessenger(Messenger messenger) {

    }

    @Override
    public Messenger getMessenger() {
        return null;
    }
}
