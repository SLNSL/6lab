package commands;

import messenger.Messenger;
import wrappers.CommandPacket;
import wrappers.Packet;

public class ClearCommandClient implements ClientCommands{
    private Messenger messenger;

    public ClearCommandClient(Messenger messenger){
        this.messenger = messenger;
    }


    @Override
    public Packet make(String message) {
        if (!message.isEmpty()){
            Packet packet = new CommandPacket(messenger.generateIncorrectNumberOfArgumentsMessage("clear", 0));
            return packet;
        }
        Packet packet = new CommandPacket("clear",  null);
        return packet;
    }
}
