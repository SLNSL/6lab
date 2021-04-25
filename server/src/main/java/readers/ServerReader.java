package readers;

import commands.Command;
import commands.SaveCommandServ;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pattern.Collection;
import wrappers.Result;

import java.util.Scanner;

public class ServerReader {
    private Collection collection;
    public static final Logger logger = LoggerFactory.getLogger("Server");

    public ServerReader(Collection collection) {
        this.collection = collection;
    }

    public void read() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine().trim();
            if (command.equals("save")) {
                Command saveCommand = new SaveCommandServ(collection);
                Result<String> result = saveCommand.execute(0, 0);
                if (result.hasError()) {
                    logger.error(result.getError());
                } else {
                    logger.info(result.getResult());
                }
            }

        }
    }
}
