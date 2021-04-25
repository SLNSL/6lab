package pattern;

import com.google.gson.Gson;
import data.Product;
import exception.*;
import messenger.Messenger;
import messenger.MessengerRu;
import checker.ServerDataChecker;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class FileManager implements Loader {

    private File file;
    private Gson gson = new Gson();

    public FileManager(String path) {
        this.file = new File(path);
    }

    /**
     * Загружает коллекцию из файла
     *
     * @return - collection
     */
    public StringBuilder load(ServerDataChecker fieldsChecker) {
        Map<Integer, Product> products = new LinkedHashMap<Integer, Product>();

        String thisLine;
        StringBuilder result = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            thisLine = reader.readLine();
            while (thisLine != null) {
                result.append(thisLine);
                thisLine = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }

    /**
     * Сохранение коллекции в файл
     *
     * @param collection
     * @throws InputOutputException - если это было исключение ввода/вывода
     */
    public void save(Map<Integer, Product> collection) throws InputOutputException {
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(gson.toJson(collection));
        } catch (IOException e) {
            throw new InputOutputException(new MessengerRu().generateInputOutputMessage());
        }
    }

}
