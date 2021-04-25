package creators;

import askers.ClientDataAsker;
import checkers.ClientDataChecker;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import data.*;
import messenger.Messenger;
import printer.Printable;
import wrappers.FieldResult;
import wrappers.Result;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

public class ObjectCreator implements ClientDataCreator {


    @Override
    public Result<Object> createProduct(boolean willExist, ClientDataChecker fieldsChecker, ClientDataAsker fieldsAsker, Printable printer) {

        boolean exit = false;
        Result<Object> field = new FieldResult<>();
        Result<Object> result = new FieldResult<>();

        Product product = new Product();
        long id;
        String name;
        Coordinates coordinates = new Coordinates();
        Long coordinatesX;
        Double coordinatesY;
        double price;
        String partNumber;
        double manufactureCost;
        UnitOfMeasure unitOfMeasure;
        Person owner = new Person();

        while (!exit) {

            field = fieldsChecker.checkName(fieldsAsker.askName());
            if (field.hasError()) {
                result.setError(field.getError());
                return result;
            } else {
                name = (String) field.getResult();
                product.setName(name);
            }


            field = fieldsChecker.checkCoordinatesX(fieldsAsker.askCoordinatesX());
            if (field.hasError()) {
                result.setError(field.getError());
                return result;
            } else {
                coordinatesX = (Long) field.getResult();
                coordinates.setX(coordinatesX);
            }

            field = fieldsChecker.checkCoordinatesY(fieldsAsker.askCoordinatesY());
            if (field.hasError()) {
                result.setError(field.getError());
                return result;
            } else {
                coordinatesY = (Double) field.getResult();
                coordinates.setY(coordinatesY);
            }

            product.setCoordinates(coordinates);

            product.setCreationDate(LocalDateTime.now());


            field = fieldsChecker.checkPrice(fieldsAsker.askPrice());
            if (field.hasError()) {
                result.setError(field.getError());
                return result;
            } else {
                price = (Double) field.getResult();
                product.setPrice(price);
            }


            field = fieldsChecker.checkPartNumber(fieldsAsker.askPartNumber());
            if (field.hasError()) {
                result.setError(field.getError());
                return result;
            } else {
                partNumber = (String) field.getResult();
                product.setPartNumber(partNumber);
            }


            field = fieldsChecker.checkManufactureCost(fieldsAsker.askManufactureCost());
            if (field.hasError()) {
                result.setError(field.getError());
                return result;
            } else {
                manufactureCost = (Double) field.getResult();
                product.setManufactureCost(manufactureCost);
            }


            field = fieldsChecker.checkUnitOfMeasure(fieldsAsker.askUnitOfMeasure());
            if (field.hasError()) {
                result.setError(field.getError());
                return result;
            } else {
                unitOfMeasure = (UnitOfMeasure) field.getResult();
                product.setUnitOfMeasure(unitOfMeasure);
            }

            field = createOwner(willExist, fieldsChecker, fieldsAsker, printer);
            if (field.hasError()) {
                result.setError(field.getError());
                break;
            } else {
                owner = (Person) field.getResult();
                product.setOwner(owner);
            }

            result.setResult(product);
            return result;
        }
        return result;
    }

    @Override
    public Result<Object> createOwner(boolean willExist, ClientDataChecker fieldsChecker, ClientDataAsker fieldsAsker, Printable printer) {

        boolean exit = false;
        Result<Object> field = new FieldResult<>();
        Result<Object> result = new FieldResult<>();

        Person owner = new Person();
        String ownerName;
        String ownerPassportId;
        Color ownerHairColor;
        Location ownerLocation = new Location();
        long ownerLocationX;
        double ownerLocationY;
        Float ownerLocationZ;
        String ownerLocationName;

        while (!exit) {
            field = fieldsChecker.checkOwnerName(fieldsAsker.askOwnerName());
            if (field.hasError()) {
                result.setError(field.getError());
                return result;
            } else {
                ownerName = (String) field.getResult();
                owner.setName(ownerName);
            }

            field = fieldsChecker.checkOwnerPassportId(fieldsAsker.askOwnerPassportID(), true);
            if (field.hasError()){
                result.setError(field.getError());
                return result;
            } else {
                ownerPassportId = (String) field.getResult();
                owner.setPassportID(ownerPassportId);
            }

            field = fieldsChecker.checkOwnerHairColor(fieldsAsker.askOwnerHairColor());
            if (field.hasError()) {
                result.setError(field.getError());
                return result;
            } else {
                ownerHairColor = (Color) field.getResult();
                owner.setHairColor(ownerHairColor);
            }


            field = fieldsChecker.checkOwnerLocationX(fieldsAsker.askOwnerLocationX());
            if (field.hasError()) {
                result.setError(field.getError());
                return result;
            } else {
                ownerLocationX = (Long) field.getResult();
                ownerLocation.setX(ownerLocationX);
            }


            field = fieldsChecker.checkOwnerLocationY(fieldsAsker.askOwnerLocationY());
            if (field.hasError()) {
                result.setError(field.getError());
                return result;
            } else {
                ownerLocationY = (Double) field.getResult();
                ownerLocation.setY(ownerLocationY);
            }


            field = fieldsChecker.checkOwnerLocationZ(fieldsAsker.askOwnerLocationZ());
            if (field.hasError()) {
                result.setError(field.getError());
                return result;
            } else {
                ownerLocationZ = (Float) field.getResult();
                ownerLocation.setZ(ownerLocationZ);
            }

            field = fieldsChecker.checkOwnerLocationName(fieldsAsker.askOwnerLocationName());
            if (field.hasError()) {
                result.setError(field.getError());
                return result;
            } else {
                ownerLocationName = (String) field.getResult();
                ownerLocation.setName(ownerLocationName);
            }
            owner.setLocation(ownerLocation);

            result.setResult(owner);
            return result;
        }
        return result;
    }


}