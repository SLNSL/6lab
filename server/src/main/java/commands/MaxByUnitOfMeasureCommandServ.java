package commands;

import data.Product;
import exception.IncorrectNumberOfArgumentsException;
import messenger.Messenger;
import pattern.Collection;
import wrappers.FieldResult;
import wrappers.Result;

public class MaxByUnitOfMeasureCommandServ implements Command {
    private Collection collectionManager;
    private Messenger messenger;


    public MaxByUnitOfMeasureCommandServ(Collection collectionManager, Messenger messenger) {
        this.collectionManager = collectionManager;
        this.messenger = messenger;
    }


    @Override
    public Result<String> execute(int port, Object... args) throws IncorrectNumberOfArgumentsException {
        collectionManager.setMessenger(messenger);

        Result<Product> maxProductResult = collectionManager.maxByUnitOfMeasure();
        if (maxProductResult.hasError()) {
            return new FieldResult<>(maxProductResult.getError());
        }


        Product maxProduct = maxProductResult.getResult();


        Result<Integer> keyResult = collectionManager.getKeyById(maxProduct.getId());
        if (keyResult.hasError()) {
            return new FieldResult<>(keyResult.getError() + "\n");
        } else {
            String elementInfo = messenger.getFieldsInfo(keyResult.getResult(), maxProduct);
            return new FieldResult<>(elementInfo + "\n" + messenger.commandIsFinished("max_by_unit_of_measure") + "\n");
        }

    }

    @Override
    public void setMessenger(Messenger messenger) {
        this.messenger = messenger;
    }

    @Override
    public Messenger getMessenger() {
        return messenger;
    }
}
