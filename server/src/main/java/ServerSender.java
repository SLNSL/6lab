

import org.slf4j.Logger;
import serverInterfaces.ServerSenderInterface;
import wrappers.Answer;
import wrappers.AnswerPacket;

import javax.xml.crypto.Data;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServerSender implements ServerSenderInterface {

    public static final Logger logger = Server.logger;

    private final static int MAX_BUFFER_SIZE = 4000;

    private DatagramSocket socket;

    private String message;

    public ServerSender(DatagramSocket socket) {
        this.socket = socket;
    }

    public void send(Answer packet, InetAddress address, int port) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(packet);
            objectOutputStream.flush();

            byte[] sendBuffer = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.flush();

            DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, address, port);


            if (sendBuffer.length > MAX_BUFFER_SIZE * 2) {
                logger.info("Размер отправленного пакета слишком большой. Пакет будет отправлен по частям");
                partialSend(packet, address, port);
            } else {
                if (packet.hasError()) {
                    logger.info("Отправлен ответ: {" + packet.getError() + "} клиенту " + sendPacket.getAddress() + ":" + sendPacket.getPort());
                } else {
                    logger.error("Отправлен ответ: {" + packet.getResult() + "} клиенту " + sendPacket.getAddress() + ":" + sendPacket.getPort());
                }
                socket.send(sendPacket);

                objectOutputStream.close();
                byteArrayOutputStream.close();
            }

        } catch (IOException e) {
            logger.error("Не удалось отправить ответ. Ошибка ввода/вывода");
        }

    }

    public void partialSend(Answer fullAnswer, InetAddress address, int port) {
        int sizeOfFullPacket = String.valueOf(fullAnswer.getResult()).getBytes().length;

        int countOfSenders = (sizeOfFullPacket / MAX_BUFFER_SIZE + 1) / 2 + 1;
        Answer partAnswer;
        for (int i = 0; i <= countOfSenders; i++) {
            if (i == countOfSenders) {
                partAnswer = new AnswerPacket(String.valueOf(fullAnswer.getResult()).substring(i * MAX_BUFFER_SIZE));
            } else {
                partAnswer = new AnswerPacket(String.valueOf(fullAnswer.getResult()).substring(i * MAX_BUFFER_SIZE, (i + 1) * MAX_BUFFER_SIZE));
            }
            send(partAnswer, address, port);
        }
    }

}
