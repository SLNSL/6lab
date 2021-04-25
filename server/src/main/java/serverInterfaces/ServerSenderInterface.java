package serverInterfaces;

import wrappers.Answer;

import java.net.InetAddress;

public interface ServerSenderInterface {

    void send(Answer packet, InetAddress address, int port);

    void partialSend(Answer fullAnswer, InetAddress address, int port);


}
