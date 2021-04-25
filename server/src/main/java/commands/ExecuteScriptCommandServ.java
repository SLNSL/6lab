package commands;

import exception.IncorrectNumberOfArgumentsException;
import messenger.Messenger;
import wrappers.FieldResult;
import wrappers.Result;

public class ExecuteScriptCommandServ implements Command {
    private Messenger messenger;


    public ExecuteScriptCommandServ(Messenger messenger) {
        this.messenger = messenger;
    }

    @Override
    public Result<String> execute(int port, Object... args) throws IncorrectNumberOfArgumentsException {
        return new FieldResult<>(messenger.commandIsFinished("execute_script " + (String) args[0]));
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
