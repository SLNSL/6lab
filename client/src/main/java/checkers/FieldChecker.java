package checkers;

import data.Color;
import data.Coordinates;
import data.Person;
import data.UnitOfMeasure;
import messenger.Messenger;
import wrappers.FieldResult;
import wrappers.Result;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class FieldChecker implements ClientDataChecker {

    private Messenger messenger;
    /**
     * Мапа, хранящая по значению id существует ли этот элемент
     */
    private Map<Long, Boolean> mapOfId = new HashMap<>();
    /**
     * Мапа, хранящая по значению passportId существует ли этот элемент
     */
    private Map<String, Boolean> mapOfPassportId = new HashMap<>();

    public FieldChecker(Messenger messenger) {
        this.messenger = messenger;
    }

    /**
     * Проверяет корректно ли введено поле key
     *
     * @param string - строковое представление значения
     * @return - key
     */
    @Override
    public Result<Integer> checkKey(String string) {
        Integer key;
        Result<Integer> result = new FieldResult<>();
        try {
            key = Integer.parseInt(String.valueOf(string));
        } catch (NumberFormatException e) {
            result.setError(messenger.generateIncorrectFieldInputMessage("key", messenger.mustBeType("Integer")));
            return result;
        }
        result.setResult(Integer.parseInt(String.valueOf(key)));
        return result;
    }


    /**
     * Проверяет корректно ли введено поле name
     *
     * @param string - строковое представление значения
     * @return name
     */
    @Override
    public Result<Object> checkName(String string) {
        Result<Object> result = new FieldResult<>();
        if (string == null || String.valueOf(string).isEmpty()) {
            result.setError(messenger.generateIncorrectFieldInputMessage("name", messenger.mustBeType("String"), messenger.cantBeEmpty(), messenger.cantBe("null")));
            return result;
        }
        result.setResult(string);
        return result;
    }


    @Override
    public Result<Object> checkId(String string) {
        long id;
        Result<Object> result = new FieldResult<>();
        try {
            id = Long.parseLong(String.valueOf(string));
        } catch (NumberFormatException e) {
            result.setError(messenger.generateIncorrectFieldInputMessage("id", messenger.mustBeType("Long"), messenger.mustBe("> 0"), messenger.mustBeUnique()));
            return result;
        }

        if (id <= 0) {
            mapOfId.remove(id);
            result.setError(messenger.generateIncorrectFieldInputMessage("id", messenger.mustBeType("Long"), messenger.mustBe("> 0"), messenger.mustBeUnique()));
            return result;
        }

        result.setResult(id);
        return result;
    }

    /**
     * Проверяет корректно ли введено поле coordinates
     *
     * @param coordinates
     * @return coordinates
     */
    @Override
    public Result<Object> checkCoordinates(Coordinates coordinates) {
        Result<Object> result = new FieldResult<>();
        if (coordinates == null) {
            result.setError(messenger.generateIncorrectFieldInputMessage("coordinates", messenger.mustBeType("Coordinates"), messenger.cantBe("null")));
            return result;
        }
        result.setResult(coordinates);
        return result;
    }

    /**
     * Проверяет корректно ли введено поле coordinates.x
     *
     * @param string - строковое представление значения
     * @return coordinates.x
     */
    @Override
    public Result<Object> checkCoordinatesX(String string) {
        Long x;
        Result<Object> result = new FieldResult<>();
        try {
            x = Long.valueOf(String.valueOf(string));
        } catch (NumberFormatException e) {
            result.setError(messenger.generateIncorrectFieldInputMessage("coordinates.x", messenger.mustBeType("Long"), messenger.cantBe("null")));
            return result;
        }

        if (x == null) {
            result.setError(messenger.generateIncorrectFieldInputMessage("coordinates.x", messenger.mustBeType("Long"), messenger.cantBe("null")));
            return result;
        }
        result.setResult(x);
        return result;
    }

    /**
     * Проверяет корректно ли введено поле coordinates.y
     *
     * @param string - строковое представление значения
     * @return coordinates.y
     */
    @Override
    public Result<Object> checkCoordinatesY(String string) {
        Double y;
        Result<Object> result = new FieldResult<>();
        try {
            y = Double.valueOf(String.valueOf(string));
        } catch (NumberFormatException e) {
            result.setError(messenger.generateIncorrectFieldInputMessage("coordinates.y", messenger.mustBeType("Double"), messenger.cantBe("null")));
            return result;
        }

        if (y == null) {
            result.setError(
                    messenger.generateIncorrectFieldInputMessage("coordinates.y", messenger.mustBeType("Double"),
                            messenger.cantBe("null")));
            return result;
        }
        result.setResult(y);
        return result;
    }

    /**
     * Проверяет корректно ли введено поле creationDate
     *
     * @param creationDate
     * @return creationDate
     */
    @Override
    public Result<Object> checkCreationDate(LocalDateTime creationDate) {
        Result<Object> result = new FieldResult<>();
        if (creationDate == null) {
            result.setError(messenger.generateIncorrectFieldInputMessage("creationDate", messenger.mustBeType("LocalDateTime"), messenger.cantBe("null")));
            return result;
        }
        try {
            creationDate.getDayOfMonth();
        } catch (NullPointerException e) {
            result.setError(messenger.generateIncorrectFieldInputMessage("creationDate", messenger.mustBeType("LocalDateTime"), messenger.cantBe("null")));
            return result;
        }
        result.setResult(creationDate);
        return result;
    }

    /**
     * Проверяет корректно ли введено поле price
     *
     * @param string - строковое представление значения
     * @return price
     */
    @Override
    public Result<Object> checkPrice(String string) {
        double price;
        Result<Object> result = new FieldResult<>();
        try {
            price = Double.parseDouble(String.valueOf(string));
        } catch (NumberFormatException e) {
            result.setError(
                    messenger.generateIncorrectFieldInputMessage("price", messenger.mustBeType("double"),
                            messenger.mustBe("> 0")));
            return result;
        }

        if (price <= 0) {
            result.setError(
                    messenger.generateIncorrectFieldInputMessage("price", messenger.mustBeType("double"),
                            messenger.mustBe("> 0")));
            return result;
        }
        result.setResult(price);
        return result;
    }

    /**
     * Проверяет корректно ли введено поле partNumber
     *
     * @param string - строковое представление значения
     * @return partNumber
     */
    @Override
    public Result<Object> checkPartNumber(String string) {
        Result<Object> result = new FieldResult<>();
        if (string == "null") {
            result.setError(
                    messenger.generateIncorrectFieldInputMessage("partNumber", messenger.mustBeType("String"),
                            messenger.cantBe("null")));
            return result;
        }
        result.setResult(string);
        return result;
    }

    /**
     * Проверяет корректно ли введено поле manufactureCost
     *
     * @param string - строковое представление значения
     * @return manufactureCost
     */
    @Override
    public Result<Object> checkManufactureCost(String string) {
        double manufactureCost;
        Result<Object> result = new FieldResult<>();
        try {
            manufactureCost = Double.parseDouble(String.valueOf(string));
        } catch (NumberFormatException | NullPointerException e) {
            result.setError(
                    messenger.generateIncorrectFieldInputMessage("manufactureCost", messenger.mustBeType("double")));
            return result;
        }
        result.setResult(manufactureCost);
        return result;
    }

    /**
     * Проверяет корректно ли введено поле unitOfMeasure
     *
     * @param string - строковое представление значения
     * @return unitOfMeasure
     */
    @Override
    public Result<Object> checkUnitOfMeasure(String string) {
        UnitOfMeasure unitOfMeasure;
        Result<Object> result = new FieldResult<>();
        if (string == null) {
            result.setError(
                    messenger.generateIncorrectFieldInputMessage("unitOfMeasure", messenger.mustBeType("UnitOfMeasure"),
                            messenger.cantBe("null")));
            return result;
        }
        try {
            unitOfMeasure = UnitOfMeasure.valueOf(String.valueOf(string).toUpperCase());
        } catch (IllegalArgumentException e) {
            result.setError(
                    messenger.generateIncorrectFieldInputMessage("unitOfMeasure", messenger.mustBeType("UnitOfMeasure"),
                            messenger.cantBe("null")));
            return result;
        }
        result.setResult(unitOfMeasure);
        return result;
    }

    /**
     * Проверяет корректно ли введено поле owner
     *
     * @param owner
     * @return owner
     */
    @Override
    public Person checkOwner(Person owner) {
        return owner;
    }

    @Override
    public Result<Object> checkOwnerName(String string) {
        Result<Object> result = new FieldResult<>();
        if (string == null || String.valueOf(string).isEmpty()) {
            result.setError(
                    messenger.generateIncorrectFieldInputMessage("owner.name", messenger.mustBeType("String"),
                            messenger.cantBe("null")));
        }
        result.setResult(string);
        return result;
    }

    /**
          * Проверяет корректно ли введено поле owner.passportId
          *
          * @param string    - строковое представление значения
          * @param willExist - будет ли существовать объект с этим id
          * @return passportId
          */
    @Override
    public Result<Object> checkOwnerPassportId(String string, boolean willExist) {
        Result<Object> result = new FieldResult<>();

        result.setResult(string);
        return result;
    }


    /**
     * Проверяет корректно ли введено поле owner.hairColor
     *
     * @param string - строковое представление значения
     * @return owner.hairColor
     */
    @Override
    public Result<Object> checkOwnerHairColor(String string) {
        Color color;
        Result<Object> result = new FieldResult<>();
        try {
            color = Color.valueOf(String.valueOf(string).toUpperCase());
        } catch (IllegalArgumentException e) {
            result.setError(
                    messenger.generateIncorrectFieldInputMessage("owner.hairColor", messenger.mustBeType("Color")));
            return result;
        } catch (NullPointerException e) {
            result.setResult(null);
            return result;
        }
        result.setResult(Color.valueOf(String.valueOf(string).toUpperCase()));
        return result;
    }

    /**
     * Проверяет корректно ли введено поле owner.location.x
     *
     * @param string - строковое представление значения
     * @return owner.location.x
     */
    @Override
    public Result<Object> checkOwnerLocationX(String string) {
        long x;
        Result<Object> result = new FieldResult<>();
        try {
            x = Long.parseLong(String.valueOf(string));
        } catch (NumberFormatException e) {
            result.setError(
                    messenger.generateIncorrectFieldInputMessage("owner.location.x", messenger.mustBeType("long"),
                            messenger.mustBeUnique()));
            return result;
        }
        result.setResult(x);
        return result;
    }

    /**
     * Проверяет корректно ли введено поле owner.location.y
     *
     * @param string - строковое представление значения
     * @return owner.location.y
     */
    @Override
    public Result<Object> checkOwnerLocationY(String string) {
        double y;
        Result<Object> result = new FieldResult<>();
        try {
            y = Double.parseDouble(String.valueOf(string));
        } catch (NumberFormatException e) {
            result.setError(
                    messenger.generateIncorrectFieldInputMessage("owner.location.y", messenger.mustBeType("double"),
                            messenger.mustBeUnique()));
            return result;
        }
        result.setResult(y);
        return result;
    }

    /**
     * Проверяет корректно ли введено поле owner.location.z
     *
     * @param string - строковое представление значения
     * @return owner.location.z
     */
    @Override
    public Result<Object> checkOwnerLocationZ(String string) {
        Float z;
        Result<Object> result = new FieldResult<>();
        try {
            z = Float.parseFloat(String.valueOf(string));
        } catch (NumberFormatException | NullPointerException e) {
            result.setError(
                    messenger.generateIncorrectFieldInputMessage("owner.location.z", messenger.mustBeType("Float"),
                            messenger.cantBe("null")));
            return result;
        }
        result.setResult(z);
        return result;
    }

    /**
     * Проверяет корректно ли введено поле owner.location.name
     *
     * @param string - строковое представление значения
     * @return owner.location.name
     */
    @Override
    public Result<Object> checkOwnerLocationName(String string) {
        Result<Object> result = new FieldResult<>();
        if (string == null) {
            result.setError(
                    messenger.generateIncorrectFieldInputMessage("owner.location.name", messenger.mustBeType("String"),
                            messenger.cantBe("null")));
            return result;
        }
        result.setResult(string);
        return result;
    }
}