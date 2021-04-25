package checkers;

import data.Coordinates;
import data.Person;
import wrappers.Result;

import java.time.LocalDateTime;

/**
 * Интерфейс, имеющий методы, которые будут проверять поля на корректность
 */
public interface ClientDataChecker {

    Result<Integer> checkKey(String string);

    Result<Object> checkName(String string);

    Result<Object> checkId(String string);


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

}
