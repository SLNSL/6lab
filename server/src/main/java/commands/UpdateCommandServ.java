package commands;


import data.*;
import exception.IncorrectNumberOfArgumentsException;

import messenger.Messenger;

import checker.ServerDataChecker;
import pattern.Collection;


import wrappers.FieldResult;
import wrappers.Result;

/**
 * Команда которая заменяет элемент по полю id на ведённый
 */
public class UpdateCommandServ implements Command {

    private Collection collectionManager;
    private Messenger messenger;
    private ServerDataChecker fieldsChecker;

    public UpdateCommandServ(Collection collectionManager, Messenger messenger, ServerDataChecker fieldsChecker) {
        this.collectionManager = collectionManager;
        this.messenger = messenger;
        this.fieldsChecker = fieldsChecker;
    }

    /**
     * Выполнение команды
     *
     * @return - true - если команда работала без ошибок, false - если команда работала с ошибками
     * @throws IncorrectNumberOfArgumentsException - было передано недопустимое количество аргументов
     */
    @Override
    public Result<String> execute(int port, Object... args) throws IncorrectNumberOfArgumentsException {

        long id = (Long) args[0];
        Product product = (Product) args[1];

        fieldsChecker.setMessenger(messenger);
        collectionManager.setMessenger(messenger);

        Result<Integer> oldProductKeyResult = collectionManager.getKeyById(id);
        if (oldProductKeyResult.hasError()) {
            return new FieldResult<>(oldProductKeyResult.getError());
        }
        System.out.println(oldProductKeyResult.getResult());

        fieldsChecker.getMapOfId().remove(id);
        fieldsChecker.getMapOfPassportId().remove(collectionManager.get(oldProductKeyResult.getResult()).getResult().getOwner().getPassportID());


        Result<Object> passportIDResult = fieldsChecker.checkOwnerPassportId(product.getOwner().getPassportID().toString(), true);
        if (passportIDResult.hasError()) {
            return new FieldResult<>(passportIDResult.getError());
        }


        if (collectionManager.get(oldProductKeyResult.getResult()).getResult().getOwner() != null)
            fieldsChecker.getMapOfPassportId().put(collectionManager.get(oldProductKeyResult.getResult()).getResult().getOwner().getPassportID(), false);
        product.setId(id);
        collectionManager.add(oldProductKeyResult.getResult(), product);
        return new FieldResult<>(messenger.commandIsFinished("update") + "\n");

    }


    @Override
    public void setMessenger(Messenger messenger) {

    }

    @Override
    public Messenger getMessenger() {
        return null;
    }
}
