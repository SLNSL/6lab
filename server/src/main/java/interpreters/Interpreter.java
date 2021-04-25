package interpreters;


import checker.ServerDataChecker;
import pattern.Collection;
import wrappers.Answer;
import messenger.Messenger;

import java.net.DatagramPacket;

public interface Interpreter {

    void createCommands(Collection collectionManager, Messenger messenger, ServerDataChecker fieldsChecker);

    void putLinkToCommands();

    Answer treat(DatagramPacket datagramPacket, Messenger messenger);
}
