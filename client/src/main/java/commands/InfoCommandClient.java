package commands;

import checkers.ClientDataChecker;
import messenger.Messenger;
import wrappers.CommandPacket;
import wrappers.Packet;

public class InfoCommandClient implements ClientCommands {
    private Messenger messenger;


    public InfoCommandClient(Messenger messenger){
        this.messenger = messenger;
    }



    @Override
    public Packet make(String message) {
        if (!message.isEmpty()){
            Packet packet = new CommandPacket(messenger.generateIncorrectNumberOfArgumentsMessage("info", 0));
            return packet;
        }
        Packet packet = new CommandPacket("info", null);
        return packet;
    }
}
