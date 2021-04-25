package clientInterfaces;

import java.util.Map;
import java.util.Scanner;

public interface CommandExecutor {

    void setScanner(Scanner scanner);

    void run();

    void runCommand(String[] command);

    boolean runScript(String argument);

    void setMapOfScripts(Map<String, Boolean> mapOfScripts);
}
