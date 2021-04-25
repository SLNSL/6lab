package creators;

import askers.ClientDataAsker;
import checkers.ClientDataChecker;
import com.google.gson.Gson;
import data.Product;
import messenger.Messenger;
import printer.Printable;
import wrappers.Result;

import java.util.Map;

public interface ClientDataCreator {

    Result<Object> createProduct(boolean willExist, ClientDataChecker fieldsChecker, ClientDataAsker fieldsAsker, Printable printer);

    Result<Object> createOwner(boolean willExist, ClientDataChecker fieldsChecker, ClientDataAsker fieldsAsker, Printable printer);
}
