

import clientInterfaces.AbstractClientReceiver;
import messenger.Messenger;
import printer.Printable;
import wrappers.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.PortUnreachableException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class ClientReceiver extends AbstractClientReceiver {

    private ByteBuffer buf = ByteBuffer.allocate(8192);
    private DatagramChannel datagramChannel;
    private SocketAddress socketAddress;
    private Printable printer;
    private Messenger messenger;

    public ClientReceiver(DatagramChannel datagramChannel, SocketAddress socketAddress, Printable printer, Messenger messenger) {
        this.datagramChannel = datagramChannel;
        this.socketAddress = socketAddress;
        this.printer = printer;
        this.messenger = messenger;
    }


    @Override
    public void run() {
        try {
            datagramChannel.connect(socketAddress);
        } catch (IOException e){
            printer.printlnError(messenger.generateUnexpectedErrorMessage());
        }

        while (!isInterrupted()) {
            receive();
        }
    }


    public Answer receive() {
        try {
            buf.clear();


            datagramChannel.receive(buf);
            buf.flip();


            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buf.array());
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            Answer answer = (Answer) objectInputStream.readObject();


            if (answer.hasError()) {
                if (answer.getErrorType() != 2) printer.println(answer.getError());
                if (answer.getErrorType() == 2) printer.println(answer.getError());
            } else {
                if (!(answer.getResult() instanceof Messenger)) printer.println(answer.getResult());
            }

            buf.clear();

            datagramChannel.disconnect();

            return answer;
        } catch (PortUnreachableException | IllegalStateException e){
            printer.printlnError(messenger.generateServerUnavailable());
        } catch (IOException | ClassNotFoundException e) {
            printer.printlnError(messenger.generateUnexpectedErrorMessage());
        }
        return null;
    }


}