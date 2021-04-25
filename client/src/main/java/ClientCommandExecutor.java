

import askers.ClientDataAsker;
import clientInterfaces.CommandExecutor;
import clientInterfaces.AbstractClientReceiver;
import clientInterfaces.ClientSenderInterface;
import commands.ClientCommands;
import interpreters.CommandInterpreter;
import messenger.Messenger;
import printer.Printable;
import wrappers.Packet;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class ClientCommandExecutor implements CommandExecutor {
    private ClientSenderInterface clientSender;
    private AbstractClientReceiver clientReceiver;
    private Printable printer;
    private CommandInterpreter clientCommandInterpreter;
    private Scanner scanner;
    private Messenger messenger;
    private ClientDataAsker asker;

    private Map<String, Boolean> mapOfScripts;


    public ClientCommandExecutor(ClientSenderInterface clientSender, AbstractClientReceiver clientReceiver,
                                 Printable printer,
                                 CommandInterpreter clientCommandInterpreter,
                                 Messenger messenger,
                                 ClientDataAsker asker) {
        this.clientSender = clientSender;
        this.clientReceiver = clientReceiver;
        this.printer = printer;
        this.clientCommandInterpreter = clientCommandInterpreter;
        this.messenger = messenger;
        this.asker = asker;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public void run() {
        Packet packet;
        do {
            String fullCommand = " ";
            while (fullCommand.equals(" ")) {
                fullCommand = scanner.nextLine().trim() + " ";
            }
            String[] command = fullCommand.split(" ", 2);

            packet = clientCommandInterpreter.getCommand(command[0]).make(command[1]);
            if (packet.hasError()) {
                printer.printlnError(packet.getError());
            }

        } while (packet.hasError());


        clientSender.send(packet);


    }

    public void runCommand(String[] command) {
        String resultOfCommand = "";

        if (command[0].trim().equals("execute_script")) {
            Packet packet = clientCommandInterpreter.getCommand(command[0]).make(command[1]);
            if (packet.hasError()) {
                printer.printlnError(packet.getError());
            } else {
                runScript(command[1].trim());
                clientSender.send(packet);
            }

        } else {

            ClientCommands clientCommands = clientCommandInterpreter.getCommand(command[0].trim());
            if (clientCommands == null) {
                printer.println(messenger.generateUnknownCommandMessage());
            } else {
                Packet packet = clientCommands.make(command[1].trim());
                if (packet.hasError()) {
                    printer.printlnError(packet.getError());
                } else {
                    clientSender.send(packet);

                }
            }

        }

    }


    public boolean runScript(String argument) {
        mapOfScripts.put(argument, true);
        try (Scanner scriptScanner = new Scanner(new FileReader(argument))) {
            do {
                String fullCommand = scriptScanner.nextLine().trim() + " ";
                String[] command = fullCommand.split(" ", 2);

                asker.setScanner(scriptScanner);
                asker.setScriptMode(true);
                if (command[0].equals("execute_script")) {
                    if (mapOfScripts.get(command[1].trim()) != null && mapOfScripts.get(command[1].trim())) {
                        printer.printlnError(messenger.generateScriptRecursionMessage());
                        continue;
                    }
                }
                if (!command[0].equals("")) {
                    for (String s : command) {
                        printer.print(s + " ");
                    }
                    printer.println("");
                }

                    runCommand(command);


            } while (scriptScanner.hasNext());


            mapOfScripts.put(argument, false);

//            printer.println(messenger.scriptIsFinished(argument));
        } catch (FileNotFoundException e) {
            printer.printlnError(messenger.generateFileWasNotFoundMessage());
        } catch (NoSuchElementException e) {
            printer.printlnError(messenger.generateNoLineFoundMessage());
        }

        return true;
    }


    public void setMapOfScripts(Map<String, Boolean> mapOfScripts) {
        this.mapOfScripts = mapOfScripts;
    }
}
