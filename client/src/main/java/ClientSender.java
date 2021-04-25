

import clientInterfaces.ClientSenderInterface;
import messenger.Messenger;
import printer.Printable;
import wrappers.Packet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class ClientSender implements ClientSenderInterface {
    private ByteBuffer buf = ByteBuffer.allocate(8192);
    private SocketAddress socketAddress;
    private DatagramChannel datagramChannel;
    private Printable printer;
    private Messenger messenger;


    public ClientSender(SocketAddress socketAddress, DatagramChannel datagramChannel, Printable printer, Messenger messenger){
        this.socketAddress = socketAddress;
        this.datagramChannel = datagramChannel;
        this.printer = printer;
        this.messenger = messenger;
    }


    public void send(Packet commandPacket){
        try {
            buf.clear();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(commandPacket);
            buf.put(byteArrayOutputStream.toByteArray());
            buf.flip();
            objectOutputStream.flush();
            byteArrayOutputStream.flush();


            datagramChannel.send(buf, socketAddress);
            objectOutputStream.close();
            byteArrayOutputStream.close();


        } catch (IOException e){
            System.out.println(messenger.generateUnexpectedErrorMessage());
            e.printStackTrace();
        }
    }
}
