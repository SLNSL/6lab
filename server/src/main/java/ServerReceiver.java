

import messenger.Messenger;

import org.slf4j.Logger;

import interpreters.CommandInterpreter;
import interpreters.Interpreter;
import serverInterfaces.ServerReceiverAbstract;
import serverInterfaces.ServerSenderInterface;
import wrappers.*;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.LinkedHashMap;
import java.util.Map;


public class ServerReceiver extends ServerReceiverAbstract {

    public static final Logger logger = Server.logger;

    private byte[] receiveBuf = new byte[2048];
    private DatagramSocket socket;
    private ServerSenderInterface serverSender;
    private Server server;

    private Thread mainThread;

    private DatagramPacket previousDatagramPacket;
    private DatagramPacket thisDatagramPacket;


    private Map<Integer, Messenger> connectedClients = new LinkedHashMap<>();

    public ServerReceiver(DatagramSocket socket, ServerSenderInterface serverSender, Server server, Thread mainThread) {
        this.socket = socket;
        this.serverSender = serverSender;
        this.server = server;
        this.mainThread = mainThread;
    }

    @Override
    public void run() {
        boolean isNewClient = true;
        Messenger messenger;
        Interpreter interpreter = new CommandInterpreter(this);

        while (!isInterrupted()) {


            if (isNewClient && thisDatagramPacket != null && connectedClients.get(thisDatagramPacket.getPort()) != null) {
                isNewClient = false;
                logger.info("Получено новое подключение: " + thisDatagramPacket.getAddress() + ":" + thisDatagramPacket.getPort());

                messenger = connectedClients.get(thisDatagramPacket.getPort());
                connectedClients.put(thisDatagramPacket.getPort(), messenger);

                server.getCollectionManager().setMessenger(messenger);

                server.getCollectionManager().load(thisDatagramPacket.getAddress(), thisDatagramPacket.getPort());

                interpreter = new CommandInterpreter(this, serverSender, messenger, server.getCollectionManager(), server.getFieldChecker());


            }
            DatagramPacket datagramPacket = receive();


            if ((previousDatagramPacket != null && thisDatagramPacket.getPort() != previousDatagramPacket.getPort()) && connectedClients.get(thisDatagramPacket.getPort()) == null) {
                isNewClient = true;
            }
            Answer answer = interpreter.treat(datagramPacket, connectedClients.get(thisDatagramPacket.getPort()));
            if (answer.hasError() && answer.getErrorType() == 2)
                connectedClients.put(thisDatagramPacket.getPort(), null);
            serverSender.send(answer, datagramPacket.getAddress(), datagramPacket.getPort());


        }

    }

    public DatagramPacket receive() {
        try {
            receiveBuf = new byte[2048];

            DatagramPacket packet
                    = new DatagramPacket(receiveBuf, receiveBuf.length);

            socket.receive(packet);
            this.previousDatagramPacket = thisDatagramPacket;
            this.thisDatagramPacket = packet;

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(receiveBuf);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);

            Packet receivedPacket = (CommandPacket) objectInputStream.readObject();
            String command = receivedPacket.getCommand();
            Object[] arguments = receivedPacket.getArguments() == null ? new String[0] : receivedPacket.getArguments();
            String argumentsString = "";
            for (Object obj : arguments) {
                argumentsString += obj.toString() + " ";
            }

            logger.info("Получен пакет от " + packet.getAddress() + ":" + packet.getPort() + " {" + command + " " + argumentsString + "}");
            return packet;
        } catch (IOException | ClassNotFoundException e) {
            logger.error("Произошла ошибка ", e);
        }
        return null;
    }


    public void setMessengerToClient(int port, Messenger messenger) {
        connectedClients.put(port, messenger);
    }
}
