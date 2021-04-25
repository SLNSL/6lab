package creators;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import data.Product;
import messenger.Messenger;
import checker.ServerDataChecker;
import checker.LoadedChecker;
import serverInterfaces.ServerSenderInterface;
import wrappers.AnswerPacket;
import wrappers.Result;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class CollectionCreator implements Creator {
    private ServerSenderInterface serverSender;

    public CollectionCreator(ServerSenderInterface serverSender) {
        this.serverSender = serverSender;
    }

    public Map<Integer, Product> createCollection(StringBuilder data, Gson gson, ServerDataChecker fieldsChecker, Messenger messenger, InetAddress inetAddress, int port) {

        fieldsChecker.setMapOfId(new HashMap<>());

        fieldsChecker.setMapOfPassportId(new HashMap<>());

        Map<Integer, Product> result = new LinkedHashMap<>();

        String sendMessage = messenger.collectionIsLoaded() + "\n" + messenger.typeHelp() + "\n";

        if (data.toString().equals("")) {
            serverSender.send(new AnswerPacket(sendMessage), inetAddress, port);
            return result;
        }

        result = gson.fromJson(data.toString(), new TypeToken<LinkedHashMap<Integer, Product>>() {
        }.getType());

        Result<Map<Integer, Product>> collectionResult = LoadedChecker.checkCollection(result, fieldsChecker, serverSender, inetAddress, port);
        result = collectionResult.getResult();


        if (collectionResult.hasError())
            sendMessage = collectionResult.getError() + "\n" + messenger.collectionIsLoaded() + "\n" + messenger.typeHelp() + "\n";


        serverSender.send(new AnswerPacket(sendMessage), inetAddress, port);

        return result;
    }
}
