

import askers.ClientDataAsker;
import clientInterfaces.CommandExecutor;
import clientInterfaces.CommandReader;
import clientInterfaces.AbstractClientReceiver;
import clientInterfaces.ClientSenderInterface;
import interpreters.CommandInterpreter;
import messenger.Messenger;
import printer.Printable;

import java.util.LinkedHashMap;
import java.util.Scanner;

public class ClientCommandReader implements CommandReader {
    private ClientSenderInterface clientSender;
    private AbstractClientReceiver clientReceiver;
    private Printable printer;
    private CommandInterpreter commandInterpreter;
    private Scanner scanner;
    private ClientDataAsker asker;
    private Messenger messenger;

    public ClientCommandReader(ClientSenderInterface clientSender, AbstractClientReceiver clientReceiver, CommandInterpreter commandInterpreter,
                               Printable printer, Scanner scanner, ClientDataAsker asker,
                               Messenger messenger) {
        this.clientSender = clientSender;
        this.clientReceiver = clientReceiver;
        this.commandInterpreter = commandInterpreter;
        this.printer = printer;
        this.scanner = scanner;
        this.asker = asker;
        this.messenger = messenger;
    }


    public void read() {
        String[] command;
        String fullCommand;
        CommandExecutor clientCommandExecutor =
                new ClientCommandExecutor(clientSender, clientReceiver, printer, commandInterpreter,
                        messenger, asker);

        while (true) {
            try {
                clientCommandExecutor.setMapOfScripts(new LinkedHashMap<>());
                asker.setScriptMode(false);
                asker.setScanner(new Scanner(System.in));
                fullCommand = scanner.nextLine().trim() + " ";
                command = fullCommand.split(" ", 2);
                clientCommandExecutor.runCommand(command);
                if (command[0].trim().equals("exit") && command[1].isEmpty()) break;



            } catch (SecurityException e) {
                printer.printlnError(messenger.generateSecurityExceptionMessage());
            }
        }
    }
}
