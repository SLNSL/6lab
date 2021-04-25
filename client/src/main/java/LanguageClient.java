

import clientInterfaces.LanguageClientInterface;
import clientInterfaces.AbstractClientReceiver;
import clientInterfaces.ClientSenderInterface;
import messenger.Messenger;
import printer.Printable;
import wrappers.Answer;
import wrappers.CommandPacket;
import wrappers.Packet;

import java.util.Scanner;

public class LanguageClient implements LanguageClientInterface {
    private ClientSenderInterface clientSender;
    private AbstractClientReceiver clientReceiver;
    private Printable printer;
    private Messenger messenger;
    private Scanner scanner;


    public LanguageClient(ClientSenderInterface clientSender, AbstractClientReceiver clientReceiver, Printable printer) {
        this.clientSender = clientSender;
        this.clientReceiver = clientReceiver;
        this.printer = printer;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }


    public void setLanguage() {
        printer.println("Введите язык / Enter the language [rus, eng]: ");
        String language = scanner.nextLine().trim();
        Packet packet = new CommandPacket("setLanguage", language);
        clientSender.send(packet);
        Answer answer = clientReceiver.receive();
        if (!answer.hasError()) {
            this.messenger = (Messenger) answer.getResult();
            Answer collectionLoaded = clientReceiver.receive();
        }

    }

    public Messenger getMessenger() {
        return messenger;
    }
}
