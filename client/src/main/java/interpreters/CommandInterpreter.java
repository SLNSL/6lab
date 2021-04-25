package interpreters;

import commands.ClientCommands;

public interface CommandInterpreter {
    void generateCommandMaker();

    ClientCommands getCommand(String command);
}
