package commands;

import askers.ClientDataAsker;
import checkers.ClientDataChecker;
import creators.ClientDataCreator;
import creators.ObjectCreator;
import data.Person;
import data.Product;
import messenger.Messenger;
import printer.Printable;
import wrappers.CommandPacket;
import wrappers.Packet;
import wrappers.Result;

public class CountLessThanOwnerCommandClient implements ClientCommands{
    private Messenger messenger;
    private ClientDataChecker clientDataChecker;
    private ClientDataAsker clientDataAsker;
    private Printable printer;

    public CountLessThanOwnerCommandClient(Messenger messenger, ClientDataChecker clientDataChecker, ClientDataAsker clientDataAsker, Printable printer){
        this.messenger = messenger;
        this.clientDataAsker = clientDataAsker;
        this.clientDataChecker = clientDataChecker;
        this.printer = printer;
    }

    @Override
    public Packet make(String message) {
        if (!message.isEmpty()){
            Packet packet = new CommandPacket(messenger.generateIncorrectNumberOfArgumentsMessage("count_less_than_owner", 0));
            return packet;
        }

        ClientDataCreator clientDataCreator = new ObjectCreator();
        Person owner = new Person();
        Result<Object> ownerResult = clientDataCreator.createOwner(false, clientDataChecker, clientDataAsker, printer);
        if (ownerResult.hasError()){
            Packet packet = new CommandPacket(ownerResult.getError());
            return packet;
        }
        owner = (Person) ownerResult.getResult();

        Packet packet = new CommandPacket("count_less_than_owner", owner);
        return packet;

    }
}
