package commands;

import data.Product;
import messenger.Messenger;
import checker.ServerDataChecker;
import pattern.Collection;
import wrappers.FieldResult;
import wrappers.Result;

public class InsertCommandServ implements Command {
    private Collection collectionManager;
    private Messenger messenger;
    private ServerDataChecker fieldsChecker;

    public InsertCommandServ(Collection collectionManager, ServerDataChecker fieldsChecker, Messenger messenger) {
        this.collectionManager = collectionManager;
        this.messenger = messenger;
        this.fieldsChecker = fieldsChecker;
    }


    /**
     * Выполнение команды
     *
     * @return - true - если команда работала без ошибок, false - если команда работала с ошибками
     */
    @Override
    public Result<String> execute(int port, Object... args) {
        Integer key = (Integer) args[0];
        Product product = (Product) args[1];

        fieldsChecker.setMessenger(messenger);
        collectionManager.setMessenger(messenger);

        product.setId(collectionManager.generateID());

        Result<Object> passportIDResult = fieldsChecker.checkOwnerPassportId(product.getOwner().getPassportID().toString(), true);

        if (passportIDResult.hasError()) {
            return new FieldResult<>(passportIDResult.getError());
        }

        collectionManager.add(key, product);
        return new FieldResult<>(messenger.commandIsFinished("insert"));


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
