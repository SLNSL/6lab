

import  askers.ClientDataAsker;
import  askers.FieldAsker;
import  checkers.ClientDataChecker;
import  checkers.FieldChecker;
import  clientInterfaces.CommandReader;
import  clientInterfaces.LanguageClientInterface;
import  clientInterfaces.AbstractClientReceiver;
import  clientInterfaces.ClientSenderInterface;
import  interpreters.ClientCommandInterpreter;
import  interpreters.CommandInterpreter;
import messenger.Messenger;
import printer.Printable;
import printer.Printer;


import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;



public class Client {
    private SocketAddress address;
    private DatagramChannel datagramChannel;
    private Messenger messenger;

    private ByteBuffer buf = ByteBuffer.allocate(8192);


    public static void main(String[] args) {
        Printable printer = new Printer();




        Client client = new Client();

        client.run();
    }


    public void run(){
            Printable printer = new Printer();
            Scanner scanner = new Scanner(System.in);
            ClientSenderInterface clientSender = new ClientSender(address, datagramChannel, printer, messenger);
            AbstractClientReceiver clientReceiver = new ClientReceiver(datagramChannel, address, printer, messenger);

            LanguageClientInterface languageClient = new LanguageClient(clientSender, clientReceiver, printer);
            languageClient.setScanner(scanner);

            languageClient.setLanguage();
            Messenger messenger = languageClient.getMessenger();

            if (messenger != null) {

                ClientDataChecker clientDataChecker = new FieldChecker(messenger);
                ClientDataAsker clientDataAsker = new FieldAsker(scanner, printer, messenger);

                CommandInterpreter commandInterpreter = new ClientCommandInterpreter(messenger, clientDataChecker, clientDataAsker, printer);
                CommandReader clientReader = new ClientCommandReader(clientSender, clientReceiver, commandInterpreter, printer, scanner, clientDataAsker, messenger);
                clientDataAsker.setScanner(scanner);

                clientReceiver.start();

                clientReader.read();

            }







    }


    public Client(){
        try {
            this.address = new InetSocketAddress("localhost", 1320);
            this.datagramChannel = DatagramChannel.open();


        } catch (SocketException e){
            System.out.println("CLIENT SOCKET EX");
        } catch (IOException e){
            System.out.println("CLIENT IO");
        }

    }

    public String sendEcho(String msg) throws SocketException, IOException {
        buf.put(msg.getBytes());
        buf.flip();

        DatagramChannel sendPacket = DatagramChannel.open();
        sendPacket.connect(address);

        sendPacket.send(buf, address);
        buf.clear();

        sendPacket.receive(buf);
        buf.flip();

        Charset latin = StandardCharsets.UTF_8;
        CharBuffer latinBuffer = latin.decode(buf);
        String result = new String(latinBuffer.array());

        String received = result;
        return received;
    }

}