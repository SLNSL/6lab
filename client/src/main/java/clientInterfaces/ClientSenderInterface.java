package clientInterfaces;

import wrappers.Packet;

public interface ClientSenderInterface {

    void send(Packet commandPacket);
}
