package commands;

import exception.IncorrectNumberOfArgumentsException;
import messenger.Messenger;
import messenger.Translator;
import serverInterfaces.ServerReceiverInterface;
import wrappers.FieldResult;
import wrappers.Result;

public class LanguageCommandServ implements Command {
    private Messenger messenger;
    private ServerReceiverInterface serverReceiver;

    public LanguageCommandServ(ServerReceiverInterface serverReceiver) {
        this.serverReceiver = serverReceiver;
    }

    @Override
    public Result<String> execute(int port, Object... args) throws IncorrectNumberOfArgumentsException {
        String language = args[0].toString();

        Result<Messenger> messengerResult = new Translator().setLanguage(language.trim());
        if (messengerResult.hasError()) {
            Result<String> fieldResult = new FieldResult<>();
            fieldResult.setError(messengerResult.getError());
            return fieldResult;
        }
        Messenger messenger = messengerResult.getResult();
        this.messenger = messenger;
        serverReceiver.setMessengerToClient(port, messenger);
        return new FieldResult<>("");


    }

    @Override
    public void setMessenger(Messenger messenger) {
        this.messenger = messenger;
    }

    public Messenger getMessenger() {
        return messenger;
    }


}
