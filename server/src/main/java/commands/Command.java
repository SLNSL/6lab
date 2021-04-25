package commands;

import exception.IncorrectNumberOfArgumentsException;
import messenger.Messenger;
import wrappers.Answer;
import wrappers.Result;

import java.net.InetAddress;

public interface Command {
    Result<String> execute(int port, Object... args) throws IncorrectNumberOfArgumentsException;

    void setMessenger(Messenger messenger);

    Messenger getMessenger();
}
