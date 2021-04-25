package creators;

import com.google.gson.Gson;
import data.Product;
import messenger.Messenger;
import checker.ServerDataChecker;

import java.net.InetAddress;
import java.util.Map;

public interface Creator {
    Map<Integer, Product> createCollection(StringBuilder data, Gson gson, ServerDataChecker fieldsChecker, Messenger messenger, InetAddress inetAddress, int port);
}
