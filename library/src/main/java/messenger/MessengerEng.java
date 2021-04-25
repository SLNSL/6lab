package messenger;

public class MessengerEng extends AbstractMessenger {

    public MessengerEng() {
        setCommandsInfo();
    }

    /**
     * Устанавливает описание для каждой команды
     */
    @Override
    public void setCommandsInfo() {
        commandsInfo.put("help", "display help for available commands");
        commandsInfo.put("info",
                "output information about the collection (type, initialization date, number of elements, etc.) to the standard output stream.)");
        commandsInfo.put("show", "output all elements of the collection in a string representation to the standard output stream");
        commandsInfo.put("insert", "add a new element with the specified key");
        commandsInfo.put("update id", "update the value of a collection element whose id is equal to the specified one");
        commandsInfo.put("remove_key null", "delete an item from the collection by its key");
        commandsInfo.put("clear", "clear the collection");
        commandsInfo.put("save", "save the collection to a file");
        commandsInfo.put("execute_script file_name",
                "read and execute the script from the specified file. The script contains commands in the same form as the user enters them interactively.");
        commandsInfo.put("exit", "end the program (without saving it to a file)");
        commandsInfo.put("remove_lower", "remove all items from the collection that are smaller than the specified one");
        commandsInfo.put("replace_if_greater null", "replace the value by key if the new value is greater than the old one");
        commandsInfo.put("replace_if_lower null", "replace the value by key if the new value is less than the old one");
        commandsInfo.put("min_by_unit_of_measure", "output any object from the collection whose UnitOfMeasure field value is minimal");
        commandsInfo.put("max_by_unit_of_measure", "output any object from the collection whose UnitOfMeasure field value is the maximum");
        commandsInfo.put("count_less_than_owner", "print the number of elements whose owner field value is less than the specified value");
    }

    @Override
    public String getCommandsInfo() {
        return super.getCommandsInfo();
    }

    /**
     * Выводит информацию о коллекции
     *
     * @param collectionType - тип коллекции
     * @param elementsType   - тип элементов
     * @param initTime       - дата инициализации
     * @param number         - количество элементов
     * @return - информация
     */
    @Override
    public String getInfo(Object collectionType, Object elementsType, Object initTime, Object number) {
        return "Collection Type:" + collectionType.toString() + "\n" + "Type of items in the collection: " + elementsType.toString() + "\n" + "Initialization date: " + initTime + "\n" +
                "Number of elements in a collection: " + number.toString() + "\n";
    }

    @Override
    public String generateElementDoesntExistMessage() {
        return "The object does not exist!";
    }

    @Override
    public String generateEmptyCollectionMessage() {
        return "The collection is empty.";
    }

    @Override
    public String generateEmptyFileMessage() {
        return "Error! The file is empty.";
    }

    @Override
    public String generateErrorInScriptMessage() {
        return "Error! An error occurred while executing the script.";
    }

    @Override
    public String generateFileWasNotFoundMessage() {
        return "Error! File not found.";
    }


    @Override
    public String generateIncorrectNumberOfArgumentsMessage(String command, int numberOfArguments) {
        return "Error! The " + command + " command must take " + numberOfArguments + " argument(-s).";
    }

    @Override
    public String generateIncorrectFieldInputMessage(String field, String... rules) {
        String message = "Error! " + field + " field entered incorrectly. ";
        message += "This field ";
        for (String rule : rules) {
            message += rule + ", ";
        }
        message = message.substring(0, message.length() - 2) + ".";
        return message;
    }

    @Override
    public String generateInputOutputMessage() {
        return "Error! The boot file is a directory or cannot be opened!";
    }

    @Override
    public String generateNoLineFoundMessage() {
        return "Error! No user input detected.";
    }

    @Override
    public String generateSecurityExceptionMessage() {
        return "Error! Insufficient read / write permissions.";
    }

    @Override
    public String generateServerUnavailable() {
        return "Error! Server unavailable.";
    }

    @Override
    public String generateJsonSyntaxMessage() {
        return "Json syntax error!";
    }

    @Override
    public String generateUnexpectedErrorMessage() {
        return "Unexpected error!";
    }

    @Override
    public String generateUnknownCommandMessage() {
        return "Unknown command. " + typeHelp();
    }

    @Override
    public String generateScriptRecursionMessage() {
        return "Warning! An error was suppressed during the execution of the script.";
    }

    @Override
    public String generateIncorrectFieldInDataMessage() {
        return "Error! The fields in the file are set in an incorrect format.";
    }

    @Override
    public String cantBeEmpty() {
        return cantBe("an empty string");
    }

    @Override
    public String cantBe(Object object) {
        return "can not be " + object.toString();
    }

    @Override
    public String mustBe(Object object) {
        return "must be " + object.toString();
    }

    @Override
    public String moreThan(Object object) {
        return "more than " + object.toString();
    }

    @Override
    public String lessThan(Object object) {
        return "less than " + object.toString();
    }

    @Override
    public String mustBeUnique() {
        return mustBe("unique");
    }

    @Override
    public String mustBeType(String type) {
        return mustBe("of type " + type);
    }

    @Override
    public String scriptIsFinished(String path) {
        return "Script " + path + " has been completed.";
    }

    @Override
    public String commandIsFinished(String command) {
        return "Command " + command + " has been completed.";
    }

    @Override
    public String collectionIsLoaded() {
        return "The collection has been loaded.";
    }

    @Override
    public String collectionIsSaved() {
        return "The collection has been saved.";
    }

    @Override
    public String typeHelp() {
        return "Type help to see the list of available commands.";
    }

    @Override
    public String lessThanOwner(long i) {
        return "There are fewer elements than the one you entered: " + i;
    }

    @Override
    public String elementReplaced(boolean b) {
        if (b) {
            return "The element has been replaced.";
        }
        return "The element hasn't been replaced.";
    }


//    @Override
//    public String getProductInfo(Product product, CollectionManager collectionManager) throws IncorrectInputException {
//        return super.getProductInfo(product, collectionManager);
//    }

    @Override
    public String askKey() {
        return "Enter the key: ";
    }

    @Override
    public String askID() {
        return "Enter the id of the item you want to update: ";
    }

    @Override
    public String askName() {
        return "Enter the name field:";
    }

    @Override
    public String askCoordinates() {
        return "Enter the coordinates field:";
    }

    @Override
    public String askCoordinatesX() {
        return "Enter the coordinates.x field:";
    }

    @Override
    public String askCoordinatesY() {
        return "Enter the coordinates.y field:";
    }

    @Override
    public String askPrice() {
        return "Enter the price field:";
    }

    @Override
    public String askPartNumber() {
        return "Enter the partNumber field:";
    }

    @Override
    public String askManufactureCost() {
        return "Enter the manufactureCost field:";
    }

    @Override
    public String askUnitOfMeasure() {
        return "Enter the UnitOfMeasure field {METERS, CENTIMETERS, SQUARE_METERS, LITERS, MILLIGRAMS}:";
    }

    @Override
    public String askOwner() {
        return "Enter the owner field:";
    }

    @Override
    public String askOwnerName() {
        return "Enter the field owner.name:";
    }

    @Override
    public String askOwnerPassportID() {
        return "Enter the owner.passportID field:";
    }

    @Override
    public String askOwnerHairColor() {
        return "Enter the owner.hairColor field {GREEN, RED, BLACK, WHITE, BROWN}:";
    }

    @Override
    public String askOwnerLocation() {
        return "Enter the owner.location field:";
    }

    @Override
    public String askOwnerLocationX() {
        return "Enter the owner.location field.x:";
    }

    @Override
    public String askOwnerLocationY() {
        return "Enter the owner.location field.y:";
    }

    @Override
    public String askOwnerLocationZ() {
        return "Enter the owner.location field.z:";
    }

    @Override
    public String askOwnerLocationName() {
        return "Enter the field owner.location.name:";
    }

}
