package commands;

import data.Product;
import exception.IncorrectNumberOfArgumentsException;
import messenger.Messenger;
import pattern.Collection;
import wrappers.FieldResult;
import wrappers.Result;

public class MinByUnitOfMeasureCommandServ implements Command {
    private Collection collectionManager;
    private Messenger messenger;


    public MinByUnitOfMeasureCommandServ(Collection collectionManager, Messenger messenger) {
        this.collectionManager = collectionManager;
        this.messenger = messenger;
    }


    @Override
    public Result<String> execute(int port, Object... args) throws IncorrectNumberOfArgumentsException {
        collectionManager.setMessenger(messenger);

        Result<Product> minProductResult = collectionManager.minByUnitOfMeasure();
        if (minProductResult.hasError()) {
            return new FieldResult<>(minProductResult.getError());
        }


        Product minProduct = minProductResult.getResult();


        Result<Integer> keyResult = collectionManager.getKeyById(minProduct.getId());
        if (keyResult.hasError()) {
            return new FieldResult<>(keyResult.getError() + "\n");
        } else {
            String elementInfo = messenger.getFieldsInfo(keyResult.getResult(), minProduct);
            return new FieldResult<>(elementInfo + "\n" + messenger.commandIsFinished("min_by_unit_of_measure") + "\n");
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
