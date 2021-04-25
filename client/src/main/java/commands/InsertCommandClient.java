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

public class InsertCommandClient implements ClientCommands {
    private Messenger messenger;
    private ClientDataAsker clientDataAsker;
    private ClientDataChecker clientDataChecker;
    private Printable printer;

    public InsertCommandClient(Messenger messenger, ClientDataChecker clientDataChecker, ClientDataAsker clientDataAsker, Printable printer){
        this.messenger = messenger;
        this.clientDataAsker = clientDataAsker;
        this.clientDataChecker = clientDataChecker;
        this.printer = printer;
    }



    public Packet make(String message){


        if (!message.isEmpty()){
            Packet packet = new CommandPacket(messenger.generateIncorrectNumberOfArgumentsMessage("insert", 0));
            return packet;
        }

        Integer key;
        Result<Integer> keyResult = clientDataChecker.checkKey(clientDataAsker.askKey());
        if (keyResult.hasError()){
            Packet packet = new CommandPacket(keyResult.getError());
            return packet;
        }
        key = keyResult.getResult();


        ClientDataCreator clientDataCreator = new ObjectCreator();
        Product product = new Product();
        Result<Object> productResult = clientDataCreator.createProduct(true, clientDataChecker, clientDataAsker, printer);
        if (productResult.hasError()){
            Packet packet = new CommandPacket(productResult.getError());
            return packet;
        }
        product = (Product) productResult.getResult();

        Packet packet = new CommandPacket("insert", key, product);
        return packet;
    }
}
