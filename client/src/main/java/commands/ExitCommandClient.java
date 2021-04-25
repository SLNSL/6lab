package commands;

import messenger.Messenger;
import wrappers.CommandPacket;
import wrappers.Packet;

public class ExitCommandClient implements ClientCommands{
    private Messenger messenger;

    public ExitCommandClient(Messenger messenger){
        this.messenger = messenger;
    }



    @Override
    public Packet make(String message) {
        if (!message.isEmpty()){
            Packet packet = new CommandPacket(messenger.generateIncorrectNumberOfArgumentsMessage("exit", 0));
            return packet;
        }
        Packet packet = new CommandPacket("exit",  null);
        return packet;
    }
}
