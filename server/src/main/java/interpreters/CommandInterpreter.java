package interpreters;


import exception.IncorrectNumberOfArgumentsException;
import checker.ServerDataChecker;
import commands.*;
import pattern.Collection;
import serverInterfaces.ServerReceiverInterface;
import serverInterfaces.ServerSenderInterface;
import wrappers.*;

import messenger.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class CommandInterpreter implements Interpreter {

    /**
     * map, which contains links to the methods by their names
     */
    private HashMap<String, Command> mapOfCommands = new LinkedHashMap<>();


    private ServerSenderInterface serverSender;
    private ServerReceiverInterface serverReceiver;

    private Command help;
    private Command exit;
    private Command show;
    private Command insert;
    private Command executeScript;
    private Command info;
    private Command removeKey;
    private Command update;
    private Command clear;
    private Command removeLower;
    private Command replaceIfGreater;
    private Command replaceIfLower;
    private Command minByUnitOfMeasure;
    private Command maxByUnitOfMeasure;
    private Command countLessThanOwner;
    private Command setLanguage;


    public CommandInterpreter() {
    }

    ;

    public CommandInterpreter(ServerReceiverInterface serverReceiver) {
        this.serverReceiver = serverReceiver;
        setLanguage = new LanguageCommandServ(serverReceiver);
        mapOfCommands.put("setLanguage", this.setLanguage);

    }

    public CommandInterpreter(ServerReceiverInterface serverReceiver, ServerSenderInterface serverSender, Messenger messenger, Collection collectionManager, ServerDataChecker fieldsChecker) {
        this.serverReceiver = serverReceiver;
        this.serverSender = serverSender;
        createCommands(collectionManager, messenger, fieldsChecker);
        putLinkToCommands();

    }

    @Override
    public void createCommands(Collection collectionManager, Messenger messenger, ServerDataChecker fieldsChecker) {
        help = new HelpCommandServ(messenger);
        exit = new ExitCommandServ(collectionManager, messenger);
        show = new ShowCommandServ(collectionManager, messenger);
        setLanguage = new LanguageCommandServ(serverReceiver);
        insert = new InsertCommandServ(collectionManager, fieldsChecker, messenger);
        executeScript = new ExecuteScriptCommandServ(messenger);
        info = new InfoCommandServ(collectionManager, messenger);
        removeKey = new RemoveKeyCommandServ(collectionManager, messenger, fieldsChecker);
        update = new UpdateCommandServ(collectionManager, messenger, fieldsChecker);
        clear = new ClearCommandServ(collectionManager, messenger);
        removeLower = new RemoveLowerCommandServ(collectionManager, messenger);
        replaceIfGreater = new ReplaceIfGreaterCommandServ(collectionManager, messenger, fieldsChecker);
        replaceIfLower = new ReplaceIfLowerCommandServ(collectionManager, messenger, fieldsChecker);
        minByUnitOfMeasure = new MinByUnitOfMeasureCommandServ(collectionManager, messenger);
        maxByUnitOfMeasure = new MaxByUnitOfMeasureCommandServ(collectionManager, messenger);
        countLessThanOwner = new CountLessThanOwnerCommandServ(collectionManager, messenger);
    }

    /**
     * Кладёт в мапу ссылки на выполнение методов, которые выполняют команды
     */
    @Override
    public void putLinkToCommands() {
        mapOfCommands.put("help", this.help);
        mapOfCommands.put("exit", this.exit);
        mapOfCommands.put("show", this.show);
        mapOfCommands.put("insert", this.insert);
        mapOfCommands.put("execute_script", this.executeScript);
        mapOfCommands.put("info", this.info);
        mapOfCommands.put("remove_key", this.removeKey);
        mapOfCommands.put("update", this.update);
        mapOfCommands.put("clear", this.clear);
        mapOfCommands.put("remove_lower", this.removeLower);
        mapOfCommands.put("replace_if_greater", this.replaceIfGreater);
        mapOfCommands.put("replace_if_lower", this.replaceIfLower);
        mapOfCommands.put("min_by_unit_of_measure", this.minByUnitOfMeasure);
        mapOfCommands.put("max_by_unit_of_measure", this.maxByUnitOfMeasure);
        mapOfCommands.put("count_less_than_owner", this.countLessThanOwner);
        mapOfCommands.put("setLanguage", this.setLanguage);
    }


    public Answer treat(DatagramPacket datagramPacket, Messenger messenger) {

        try {

            byte[] buf = datagramPacket.getData();
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buf);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            Packet commandPacket = (CommandPacket) objectInputStream.readObject();

            String stringCommand = commandPacket.getCommand();


            Command command = getCommand(stringCommand);


            if (messenger != null) command.setMessenger(messenger);

            Object[] args = commandPacket.getArguments();

            String result;
            try {
                Result<String> executeResult = command.execute(datagramPacket.getPort(), args);

                if (executeResult.hasError()) return new AnswerPacket(executeResult.getError(), 2);

                if (stringCommand.equals("setLanguage")) return new AnswerPacket(command.getMessenger());
                result = executeResult.getResult();


                if (stringCommand.equals("exit")) return new AnswerPacket(result, 2);
                return new AnswerPacket(result);
            } catch (IncorrectNumberOfArgumentsException e) {
                return new AnswerPacket(e.getMessage(), 1);
            }


        } catch (IOException | ClassCastException | ClassNotFoundException e) {
            System.out.println("INTERPRET");
            e.printStackTrace();
        }

        return null;
    }


    public HashMap<String, Command> getMapOfCommands() {
        return mapOfCommands;
    }

    public Command getCommand(String stringCommand) {
        return mapOfCommands.get(stringCommand);
    }
}
