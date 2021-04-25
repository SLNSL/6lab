package checker;

import data.Coordinates;
import data.Person;
import messenger.Messenger;
import wrappers.Result;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Интерфейс, имеющий методы, которые будут проверять поля на корректность
 */
public interface ServerDataChecker {

    Result<Integer> checkKey(String string);

    Result<Object> checkId(String string, boolean willExist);

    Result<Object> checkName(String string);

    Result<Object> checkCoordinates(Coordinates coordinates);

    Result<Object> checkCoordinatesX(String string);

    Result<Object> checkCoordinatesY(String string);

    Result<Object> checkCreationDate(LocalDateTime creationDate);

    Result<Object> checkPrice(String string);

    Result<Object> checkPartNumber(String string);

    Result<Object> checkManufactureCost(String string);

    Result<Object> checkUnitOfMeasure(String string);

    Person checkOwner(Person owner);

    Result<Object> checkOwnerName(String string);

    Result<Object> checkOwnerPassportId(String string, boolean willExist);

    Result<Object> checkOwnerHairColor(String string);

    Result<Object> checkOwnerLocationX(String string);

    Result<Object> checkOwnerLocationY(String string);

    Result<Object> checkOwnerLocationZ(String string);

    Result<Object> checkOwnerLocationName(String string);

    Map<Long, Boolean> getMapOfId();

    Map<String, Boolean> getMapOfPassportId();

    boolean isIdExist(long id);

    boolean isPassportIdExist(String passportId);

    void setMapOfId(Map<Long, Boolean> mapOfId);

    void setMapOfPassportId(Map<String, Boolean> mapOfPassportId);

    void setMessenger(Messenger messenger);
}


