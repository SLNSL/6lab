package commands;

import messenger.Messenger;
import wrappers.CommandPacket;
import wrappers.Packet;

public class ExecuteScriptCommandClient implements ClientCommands{
    private Messenger messenger;


    public ExecuteScriptCommandClient(Messenger messenger){
        this.messenger = messenger;
    }


    @Override
    public Packet make(String message) {
        if (message.isEmpty()){
            Packet packet = new CommandPacket(messenger.generateIncorrectNumberOfArgumentsMessage("execute_script", 1));
            return packet;
        }

        return new CommandPacket("execute_script",message);
    }
}
