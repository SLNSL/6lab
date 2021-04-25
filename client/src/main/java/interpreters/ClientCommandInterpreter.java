package interpreters;

import askers.ClientDataAsker;
import checkers.ClientDataChecker;
import commands.*;
import messenger.Messenger;
import printer.Printable;
import wrappers.Packet;

import java.util.HashMap;

public class ClientCommandInterpreter implements CommandInterpreter{
    private Messenger messenger;
    private ClientDataAsker clientDataAsker;
    private ClientDataChecker clientDataChecker;
    private Printable printer;

    private HashMap<String, ClientCommands> commandMaker = new HashMap<>();

    public ClientCommandInterpreter(Messenger messenger, ClientDataChecker clientDataChecker, ClientDataAsker clientDataAsker, Printable printer){
        this.messenger = messenger;
        this.printer = printer;
        this.clientDataAsker = clientDataAsker;
        this.clientDataChecker = clientDataChecker;
        generateCommandMaker();
    }

    @Override
    public void generateCommandMaker(){
        commandMaker.put("insert", new InsertCommandClient(messenger, clientDataChecker, clientDataAsker, printer));
        commandMaker.put("help", new HelpCommandClient(messenger));
        commandMaker.put("show", new ShowCommandClient(messenger));
        commandMaker.put("exit", new ExitCommandClient(messenger));
        commandMaker.put("execute_script", new ExecuteScriptCommandClient(messenger));
        commandMaker.put("update", new UpdateCommandClient(messenger, clientDataChecker, clientDataAsker, printer));
        commandMaker.put("info", new InfoCommandClient(messenger));
        commandMaker.put("remove_key", new RemoveKeyCommandClient(messenger, clientDataChecker));
        commandMaker.put("clear", new ClearCommandClient(messenger));
        commandMaker.put("remove_lower", new RemoveLowerCommandClient(messenger, clientDataChecker, clientDataAsker, printer));
        commandMaker.put("replace_if_greater", new ReplaceIfGreaterCommandClient(messenger, clientDataChecker, clientDataAsker, printer));
        commandMaker.put("replace_if_lower", new ReplaceIfLowerCommandClient(messenger, clientDataChecker, clientDataAsker, printer));
        commandMaker.put("min_by_unit_of_measure", new MinByUnitOfMeasureCommandClient(messenger));
        commandMaker.put("max_by_unit_of_measure", new MaxByUnitOfMeasureCommandClient(messenger));
        commandMaker.put("count_less_than_owner", new CountLessThanOwnerCommandClient(messenger, clientDataChecker, clientDataAsker, printer));

    }

    @Override
    public ClientCommands getCommand(String command){
        return commandMaker.get(command);
    }

}
