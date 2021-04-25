


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import checker.FieldChecker;
import checker.ServerDataChecker;
import pattern.Collection;
import pattern.CollectionManager;
import pattern.FileManager;
import pattern.Loader;
import readers.ServerReader;
import serverInterfaces.ServerReceiverAbstract;
import serverInterfaces.ServerSenderInterface;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.*;

public class Server {
    private DatagramSocket socket;
    private boolean status;
    private byte[] receiveBuf = new byte[2048];
    private byte[] sendBuf = new byte[2048];
    private InetAddress inetAddress;
    private int port;


    private Collection collectionManager;

    private ServerDataChecker fieldChecker;

    private Loader fileLoader;

    public static final Logger logger = LoggerFactory.getLogger(Server.class);


    public static void main(String[] args) {
        disableAccessWarnings();

        Server server = new Server();
        logger.info("Сервер запущен");
        server.run();

    }

    public Server() {
        try {
            this.inetAddress = InetAddress.getByName("localhost");
            this.port = 1320;
            this.socket = new DatagramSocket(port);
        } catch (SocketException e) {
            System.out.println("SERVER 12!!!!");
            e.printStackTrace();
        } catch (UnknownHostException e) {
            System.out.println("SERVER UNKNOWN");
        }

    }


    public void run() {
        ServerSenderInterface serverSender = new ServerSender(socket);
        ServerReceiverAbstract serverReceiver = new ServerReceiver(socket, serverSender, this, Thread.currentThread());


        this.fieldChecker = new FieldChecker();

        this.fileLoader = new FileManager(System.getenv("MyProducts"));

        this.collectionManager = new CollectionManager
                (fileLoader, fieldChecker, serverSender);


        serverReceiver.setDaemon(true);
        serverReceiver.start();


        ServerReader serverReader = new ServerReader(collectionManager);
        serverReader.read();


    }


    @SuppressWarnings("unchecked")
    public static void disableAccessWarnings() {
        try {
            Class unsafeClass = Class.forName("sun.misc.Unsafe");
            Field field = unsafeClass.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            Object unsafe = field.get(null);

            Method putObjectVolatile = unsafeClass.getDeclaredMethod("putObjectVolatile", Object.class, long.class, Object.class);
            Method staticFieldOffset = unsafeClass.getDeclaredMethod("staticFieldOffset", Field.class);

            Class loggerClass = Class.forName("jdk.internal.module.IllegalAccessLogger");
            Field loggerField = loggerClass.getDeclaredField("logger");
            Long offset = (Long) staticFieldOffset.invoke(unsafe, loggerField);
            putObjectVolatile.invoke(unsafe, loggerClass, offset, null);
        } catch (Exception ignored) {
        }
    }

    public Collection getCollectionManager() {
        return collectionManager;
    }

    public ServerDataChecker getFieldChecker() {
        return fieldChecker;
    }

    public Loader getFileLoader() {
        return fileLoader;
    }
}
