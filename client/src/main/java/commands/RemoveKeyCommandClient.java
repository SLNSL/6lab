package commands;

import checkers.ClientDataChecker;
import messenger.Messenger;
import wrappers.CommandPacket;
import wrappers.Packet;
import wrappers.Result;

public class RemoveKeyCommandClient implements ClientCommands {
    private Messenger messenger;
    private ClientDataChecker clientDataChecker;

    public RemoveKeyCommandClient(Messenger messenger, ClientDataChecker clientDataChecker){
        this.messenger = messenger;
        this.clientDataChecker = clientDataChecker;
    }


    @Override
    public Packet make(String message) {
        if (message.isEmpty()){
            Packet packet = new CommandPacket(messenger.generateIncorrectNumberOfArgumentsMessage("remove_key", 1));
            return packet;
        }

        Result<Integer> keyResult = clientDataChecker.checkKey(message);
        if (keyResult.hasError()){
            return new CommandPacket(keyResult.getError());
        }
        Integer key = keyResult.getResult();
        return new CommandPacket("remove_key", key);


    }
}
