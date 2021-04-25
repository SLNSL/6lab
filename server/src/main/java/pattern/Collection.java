package pattern;

import data.Person;
import data.Product;
import exception.EmptyCollectionException;
import messenger.Messenger;
import wrappers.Result;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Map;

public interface Collection {
    void load(InetAddress inetAddress, int port);

    Result<Object> delete(Integer key);

    void save();

    void add(Integer key, Product product);

    void clear();

    String info();

    long countLessThanOwner(Person person);

    String writeCollection() throws EmptyCollectionException;

    long generateID();

    Result<Product> getProductById(long id);

    Result<Integer> getKeyById(long id);

    public Product getFirst();

    Result<Product> minByUnitOfMeasure();

    Result<Product> maxByUnitOfMeasure();

    Result<Product> get(Integer key);

    long removeLower(Product product);

    Result<Boolean> replaceIfGreater(Integer key, Product p1);

    Result<Boolean> replaceIfLower(Integer key, Product p1);

    void sort();

    Integer getKey(Product product);

    Map<Integer, Product> getProductCollection();

    void setMessenger(Messenger messenger);

}



