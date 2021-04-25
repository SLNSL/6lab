package commands;

import data.Product;
import exception.IncorrectNumberOfArgumentsException;
import messenger.Messenger;
import checker.ServerDataChecker;
import pattern.Collection;
import wrappers.FieldResult;
import wrappers.Result;

public class ReplaceIfLowerCommandServ implements Command {
    private Collection collectionManager;
    private Messenger messenger;
    private ServerDataChecker fieldsChecker;

    public ReplaceIfLowerCommandServ(Collection collectionManager, Messenger messenger, ServerDataChecker serverDataChecker) {
        this.messenger = messenger;
        this.collectionManager = collectionManager;
        this.fieldsChecker = serverDataChecker;
    }


    @Override
    public Result<String> execute(int port, Object... args) throws IncorrectNumberOfArgumentsException {

        Integer key = (Integer) args[0];
        Product product = (Product) args[1];

        collectionManager.setMessenger(messenger);
        fieldsChecker.setMessenger(messenger);

        if (collectionManager.get(key).hasError()) {
            return new FieldResult<>(messenger.generateElementDoesntExistMessage());
        }


        product.setId(collectionManager.get(key).getResult().getId());

        fieldsChecker.getMapOfPassportId().remove(collectionManager.get(key).getResult().getOwner().getPassportID());


        Result<Object> passportIDResult = fieldsChecker.checkOwnerPassportId(product.getOwner().getPassportID().toString(), true);
        if (passportIDResult.hasError()) {
            return new FieldResult<>(passportIDResult.getError());
        }


        Result<Boolean> replaceOrNot = collectionManager.replaceIfLower(key, product);

        if (replaceOrNot.hasError()) {
            return new FieldResult<>(replaceOrNot.getError());
        }
        String result = messenger.elementReplaced(replaceOrNot.getResult());

        if (!replaceOrNot.getResult() && collectionManager.get(key).getResult().getOwner() != null) {
            fieldsChecker.getMapOfPassportId().put(collectionManager.get(key).getResult().getOwner().getPassportID(), true);
        }

        return new FieldResult<>(result + "\n" + messenger.commandIsFinished("remove_if_lower"));

    }

    @Override
    public void setMessenger(Messenger messenger) {

    }

    @Override
    public Messenger getMessenger() {
        return null;
    }
}
