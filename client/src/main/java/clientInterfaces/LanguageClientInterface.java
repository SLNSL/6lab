package clientInterfaces;

import messenger.Messenger;

import java.util.Scanner;

public interface LanguageClientInterface {
    void setLanguage();

    void setScanner(Scanner scanner);

    Messenger getMessenger();
}
