package messenger;

public class MessengerRu extends AbstractMessenger {

    public MessengerRu() {
        setCommandsInfo();
    }

    @Override
    public void setCommandsInfo() {
        commandsInfo.put("help", "вывести справку по доступным командам");
        commandsInfo.put("info", "вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
        commandsInfo.put("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        commandsInfo.put("insert", "добавить новый элемент с заданным ключом");
        commandsInfo.put("update id", "обновить значение элемента коллекции, id которого равен заданному");
        commandsInfo.put("remove_key null", "удалить элемент из коллекции по его ключу");
        commandsInfo.put("clear", "очистить коллекцию");
        commandsInfo.put("save", "сохранить коллекцию в файл");
        commandsInfo.put("execute_script file_name", " считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");
        commandsInfo.put("exit", "завершить программу (без сохранения в файл)");
        commandsInfo.put("remove_lower", "удалить из коллекции все элементы, меньшие, чем заданный");
        commandsInfo.put("replace_if_greater null", "заменить значение по ключу, если новое значение больше старого");
        commandsInfo.put("replace_if_lower null", "заменить значение по ключу, если новое значение меньше старого");
        commandsInfo.put("min_by_unit_of_measure", "вывести любой объект из коллекции, значение поля unitOfMeasure которого является минимальным");
        commandsInfo.put("max_by_unit_of_measure", "вывести любой объект из коллекции, значение поля unitOfMeasure которого является максимальным");
        commandsInfo.put("count_less_than_owner", "вывести количество элементов, значение поля owner которых меньше заданного");
    }

    @Override
    public String getCommandsInfo() {
        return super.getCommandsInfo();
    }

    @Override
    public String getInfo(Object collectionType, Object elementsType, Object initTime, Object number) {
        return "Тип коллекции: " + collectionType.toString() + "\n" + "Тип элементов в коллекции: " + elementsType.toString() + "\n" + "Дата инициализации: " + initTime + "\n" +
                "Количество элементов в коллеции: " + number.toString() + "\n";
    }

    @Override
    public String generateElementDoesntExistMessage() {
        return "Объект не существует!";
    }

    @Override
    public String generateEmptyCollectionMessage() {
        return "Коллекция пуста.";
    }

    @Override
    public String generateEmptyFileMessage() {
        return "Файл пуст.";
    }

    @Override
    public String generateErrorInScriptMessage() {
        return "Произошла ошибка во время выполнения скрипта.";
    }

    @Override
    public String generateFileWasNotFoundMessage() {
        return "Файл не найден.";
    }


    @Override
    public String generateIncorrectNumberOfArgumentsMessage(String command, int numberOfArguments) {
        return "Ошибка! Команда " + command + " должна принимать " + numberOfArguments + " аргумент(-а/-ов)";
    }

    @Override
    public String generateIncorrectFieldInputMessage(String field, String... rules) {
        String message = "Ошибка! Поле " + field + " введено неверно. ";
        message += "Поле ";
        for (String rule : rules) {
            message += rule + ", ";
        }
        message = message.substring(0, message.length() - 2) + ".";
        return message;
    }

    @Override
    public String generateInputOutputMessage() {
        return "Ошибка! Загрузочный файл является директорией или не может быть открыт!";
    }

    @Override
    public String generateNoLineFoundMessage() {
        return "Ошибка! Пользовательский ввод не обнаружен.";
    }

    @Override
    public String generateSecurityExceptionMessage() {
        return "Ошибка! Недостаточно прав для чтения/записи.";
    }

    @Override
    public String generateServerUnavailable() {
        return "Ошибка! Сервер недоступен.";
    }

    @Override
    public String generateJsonSyntaxMessage() {
        return "Ошибка синтаксиса Json!";
    }

    @Override
    public String generateUnexpectedErrorMessage() {
        return "Непредвиденная ошибка!";
    }

    @Override
    public String generateUnknownCommandMessage() {
        return "Неизвестная команда. " + typeHelp();
    }

    @Override
    public String generateScriptRecursionMessage() {
        return "Предупреждение! Во время выполнения скрипта была подавлена ошибка.";
    }

    @Override
    public String generateIncorrectFieldInDataMessage() {
        return "Ошибка! Поля в файле заданы в некорректном формате.";
    }

    @Override
    public String cantBeEmpty() {
        return cantBe("пустой строкой");
    }

    @Override
    public String cantBe(Object object) {
        return "не может быть " + object.toString();
    }

    @Override
    public String mustBe(Object object) {
        return "должно быть " + object.toString();
    }

    @Override
    public String moreThan(Object object) {
        return "больше чем " + object.toString();
    }

    @Override
    public String lessThan(Object object) {
        return "меньше чем " + object.toString();
    }

    @Override
    public String mustBeUnique() {
        return mustBe("уникальным");
    }

    @Override
    public String mustBeType(String type) {
        return mustBe("типа " + type);
    }

    @Override
    public String scriptIsFinished(String path) {
        return "Скрипт " + path + " выполнен.";
    }

    @Override
    public String commandIsFinished(String command) {
        return "Команда " + command + " была выполнена.";
    }

    @Override
    public String collectionIsLoaded() {
        return "Коллекция загружена.";
    }

    @Override
    public String collectionIsSaved() {
        return "Коллекция сохранена.";
    }

    @Override
    public String typeHelp() {
        return "Введите help, чтобы увидеть список доступных команд.";
    }

    @Override
    public String lessThanOwner(long i) {
        return "Элементов меньше чем введённый: " + i;
    }

    @Override
    public String elementReplaced(boolean b) {
        if (b) {
            return "Элемент был заменён.";
        }
        return "Элемент не был заменён.";
    }


//    @Override
//    public String getProductInfo(Product product, CollectionManager collectionManager) throws IncorrectInputException {
//        return super.getProductInfo(product, collectionManager);
//    }

    @Override
    public String askKey() {
        return "Введите ключ: ";
    }

    @Override
    public String askID() {
        return "Введите id элемента, который хотите обновить:";
    }

    @Override
    public String askName() {
        return "Введите поле name:";
    }

    @Override
    public String askCoordinates() {
        return "Введите поле coordinates:";
    }

    @Override
    public String askCoordinatesX() {
        return "Введите поле coordinates.x:";
    }

    @Override
    public String askCoordinatesY() {
        return "Введите поле coordinates.y:";
    }

    @Override
    public String askPrice() {
        return "Введите поле price:";
    }

    @Override
    public String askPartNumber() {
        return "Введите поле partNumber:";
    }

    @Override
    public String askManufactureCost() {
        return "Введите поле manufactureCost:";
    }

    @Override
    public String askUnitOfMeasure() {
        return "Введите поле unitOfMeasure {METERS, CENTIMETERS, SQUARE_METERS, LITERS, MILLIGRAMS}:";
    }

    @Override
    public String askOwner() {
        return "Введите поле owner:";
    }

    @Override
    public String askOwnerName() {
        return "Введите поле owner.name:";
    }

    @Override
    public String askOwnerPassportID() {
        return "Введите поле owner.passportID:";
    }

    @Override
    public String askOwnerHairColor() {
        return "Введите поле owner.hairColor {GREEN, RED, BLACK, WHITE, BROWN}:";
    }

    @Override
    public String askOwnerLocation() {
        return "Введите поле owner.location:";
    }

    @Override
    public String askOwnerLocationX() {
        return "Введите поле owner.location.x:";
    }

    @Override
    public String askOwnerLocationY() {
        return "Введите поле owner.location.y:";
    }

    @Override
    public String askOwnerLocationZ() {
        return "Введите поле owner.location.z:";
    }

    @Override
    public String askOwnerLocationName() {
        return "Введите поле owner.location.name:";
    }

}
