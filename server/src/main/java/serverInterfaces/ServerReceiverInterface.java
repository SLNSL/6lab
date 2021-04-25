package serverInterfaces;

import messenger.Messenger;

import java.net.DatagramPacket;

public interface ServerReceiverInterface {

    DatagramPacket receive();

    void setMessengerToClient(int port, Messenger messenger);
}
