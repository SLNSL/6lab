package commands;

import askers.ClientDataAsker;
import checkers.ClientDataChecker;
import creators.ClientDataCreator;
import creators.ObjectCreator;
import data.Product;
import messenger.Messenger;
import printer.Printable;
import wrappers.CommandPacket;
import wrappers.Packet;
import wrappers.Result;

public class RemoveLowerCommandClient  implements ClientCommands{
    private Messenger messenger;
    private ClientDataChecker clientDataChecker;
    private ClientDataAsker clientDataAsker;
    private Printable printer;

    public RemoveLowerCommandClient(Messenger messenger, ClientDataChecker clientDataChecker, ClientDataAsker clientDataAsker, Printable printer){
        this.messenger = messenger;
        this.clientDataChecker = clientDataChecker;
        this.clientDataAsker = clientDataAsker;
        this.printer = printer;
    }

    @Override
    public Packet make(String message) {
        if (!message.isEmpty()){
            Packet packet = new CommandPacket(messenger.generateIncorrectNumberOfArgumentsMessage("remove_lower", 0));
            return packet;
        }

        ClientDataCreator clientDataCreator = new ObjectCreator();
        Product product;
        Result<Object> productResult = clientDataCreator.createProduct(false, clientDataChecker, clientDataAsker, printer);
        if (productResult.hasError()){
            Packet packet = new CommandPacket(productResult.getError());
            return packet;
        }
        product = (Product) productResult.getResult();

        Packet packet = new CommandPacket("remove_lower", product);
        return packet;

    }
}
