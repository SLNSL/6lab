package commands;

import messenger.Messenger;
import wrappers.CommandPacket;
import wrappers.Packet;

public class MaxByUnitOfMeasureCommandClient implements ClientCommands{
    private Messenger messenger;


    public MaxByUnitOfMeasureCommandClient(Messenger messenger){
        this.messenger = messenger;
    }


    @Override
    public Packet make(String message) {
        if (!message.isEmpty()){
            Packet packet = new CommandPacket(messenger.generateIncorrectNumberOfArgumentsMessage("max_by_unit_of_measure", 0));
            return packet;
        }
        Packet packet = new CommandPacket("max_by_unit_of_measure", null);
        return packet;
    }

}
