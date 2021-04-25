package messenger;

import data.Product;

/**
 * Интерфейс для классов Messenger с разными языками.
 */
public interface Messenger {

    //  Спрашиватель полей у элемента

    /**
     * Запрашивает ключ
     *
     * @return - запрос ключа
     */
    String askKey();

    /**
     * Запрашивает id
     *
     * @return запрос id
     */
    String askID();

    /**
     * Запрашивает name
     *
     * @return запрос name
     */
    String askName();

    /**
     * Запрашивает coordinates
     *
     * @return запрос coordinates
     */
    String askCoordinates();

    /**
     * Запрашивает coordinates.x
     *
     * @return запрос coordinates.x
     */
    String askCoordinatesX();

    /**
     * Запрашивает coordinates.y
     *
     * @return запрос coordinates.y
     */
    String askCoordinatesY();

    /**
     * Запрашивает price
     *
     * @return запрос price
     */
    String askPrice();

    /**
     * Запрашивает partNumber
     *
     * @return запрос partNumber
     */
    String askPartNumber();

    /**
     * Запрашивает manufactureCost
     *
     * @return запрос manufactureCost
     */
    String askManufactureCost();

    /**
     * Запрашивает unitOfMeasure
     *
     * @return запрос unitOfMeasure
     */
    String askUnitOfMeasure();

    /**
     * Запрашивает owner
     *
     * @return запрос owner
     */
    String askOwner();

    /**
     * Запрашивает owner.name
     *
     * @return запрос owner.name
     */
    String askOwnerName();

    /**
     * Запрашивает owner.passportID
     *
     * @return запрос owner.passportID
     */
    String askOwnerPassportID();

    /**
     * Запрашивает price
     *
     * @return запрос price
     */
    String askOwnerHairColor();

    /**
     * Запрашивает owner.location
     *
     * @return запрос owner.location
     */
    String askOwnerLocation();

    /**
     * Запрашивает owner.location.x
     *
     * @return запрос owner.location.x
     */
    String askOwnerLocationX();

    /**
     * Запрашивает owner.location.y
     *
     * @return запрос owner.location.y
     */
    String askOwnerLocationY();

    /**
     * Запрашивает owner.location.z
     *
     * @return запрос owner.location.z
     */
    String askOwnerLocationZ();

    /**
     * Запрашивает owner.location.name
     *
     * @return запрос owner.location.name
     */
    String askOwnerLocationName();

    //  Информация о командах и коллекции.

    /**
     * Устанавливает описание для команд
     */
    void setCommandsInfo();

    /**
     * Возврашает описание для команд
     *
     * @return описани для команд
     */
    String getCommandsInfo();

    /**
     * Возвращает информацию о полях продукта
     *
     * @param product - продукт
     * @return - информация
     */
    String getFieldsInfo(Integer key, Product product);

    /**
     * Возвращает информацию о коллекции
     *
     * @param collectionType - тип коллекции
     * @param elementsType   - тип элементов
     * @param initTime       - дата инициализации
     * @param number         - количество элементов в коллекции
     * @return информация
     */
    String getInfo(Object collectionType, Object elementsType, Object initTime, Object number);

    //  Сообщения для ошибок.

    /**
     * Генерирует сообщение для ошибки когда элемент не существует
     *
     * @return сообщение
     */
    String generateElementDoesntExistMessage();

    /**
     * Генерирует сообщение для ошибки когда коллекция пуста
     *
     * @return сообщение
     */
    String generateEmptyCollectionMessage();

    /**
     * Генерирует сообщение для ошибки когда файл пуст
     *
     * @return сообщение
     */
    String generateEmptyFileMessage();

    /**
     * Генерирует сообщение для ошибки когда произошла ошибка в скрипте
     *
     * @return сообщение
     */
    String generateErrorInScriptMessage();

    /**
     * Генерирует сообщение для ошибки когда файл не найден
     *
     * @return сообщение
     */
    String generateFileWasNotFoundMessage();

    /**
     * Генерирует сообщение для ошибки когда введено неверное число аргументов
     *
     * @param command           - команда
     * @param NumberOfArguments - верное число аргументов
     * @return - сообщение
     */
    String generateIncorrectNumberOfArgumentsMessage(String command, int NumberOfArguments);

    /**
     * Генерирует сообщение для ошибки когда ввод поля был неверным
     *
     * @param field - поле
     * @param rules - правила для ввода этого поля
     * @return
     */
    String generateIncorrectFieldInputMessage(String field, String... rules);

    /**
     * Генерирует сообщение для ошибки когда произошла ошибка ввода/вывода
     *
     * @return сообщение
     */
    String generateInputOutputMessage();

    /**
     * Генерирует сообщение для ошибки когда не был найден пользовательский ввод
     *
     * @return сообщение
     */
    String generateNoLineFoundMessage();

    /**
     * Генерирует сообщение для ошибки когда произошёл security exception
     *
     * @return сообщение
     */
    String generateSecurityExceptionMessage();

    String generateServerUnavailable();

    /**
     * Генерирует сообщение для ошибки когда произошла ошибка синтаксиса json
     *
     * @return сообщение
     */
    String generateJsonSyntaxMessage();

    /**
     * Генерирует сообщение для ошибки когда произошла непредвиденная ошибка
     *
     * @return сообщение
     */
    String generateUnexpectedErrorMessage();

    /**
     * Генерирует сообщение для ошибки когда была введена неизвестная команда
     *
     * @return сообщение
     */
    String generateUnknownCommandMessage();

    /**
     * Генерирует сообщение для ошибки когда была выявлена рекурсия в скрипте
     *
     * @return сообщение
     */
    String generateScriptRecursionMessage();

    /**
     * Генерирует сообщение для ошибки когда поля в файле было введено неверно
     *
     * @return сообщение
     */
    String generateIncorrectFieldInDataMessage();

    //  Правила для полей.

    /**
     * Возвращает сообщение о том, что поле не может быть пустым
     *
     * @return сообщение
     */
    String cantBeEmpty();

    /**
     * Возвращает сообщение о том, что поле может быть *object*
     *
     * @param object - правило
     * @return сообщение
     */
    String cantBe(Object object);

    /**
     * Возвращает сообщение о том, что поле должно быть *object*
     *
     * @param object - правило
     * @return - сообщение
     */
    String mustBe(Object object);

    /**
     * Возвращает сообщение о том, что поле должно быть больше *object*
     *
     * @param object - правило
     * @return сообщение
     */
    String moreThan(Object object);

    /**
     * Возвращает сообщение о том, что поле должно быть меньше *object*
     *
     * @param object - правило
     * @return сообщение
     */
    String lessThan(Object object);

    /**
     * Возвращает сообщение о том, что поле должно быть уникальным
     *
     * @return сообщение
     */
    String mustBeUnique();

    /**
     * Возвращает сообщение о том, что поле должно быть типа *type*
     *
     * @param type - тип
     * @return сообщение
     */
    String mustBeType(String type);

    //  Прочие сообщения.

    /**
     * Возвращает сообщение о том, что скрипт завершён
     *
     * @param path - путь до скрипта
     * @return сообщение
     */
    String scriptIsFinished(String path);

    /**
     * Возвращает сообщение о том, что команда завершёна
     *
     * @param command - имя команды
     * @return сообщение
     */
    String commandIsFinished(String command);

    /**
     * Вовзращает сообщение о том, что коллекция была загружена.
     *
     * @return сообщение
     */
    String collectionIsLoaded();

    /**
     * Вовзращает сообщение о том, что коллекция была сохранена.
     *
     * @return сообщение
     */
    String collectionIsSaved();

    /**
     * Возвращает сообщение о том, что чтобы узнать список команд - нужно ввести help
     *
     * @return сообщение
     */
    String typeHelp();

    /**
     * Возвращает сообщение с информацией о том, сколько элементов с полем owner меньше чем введённый
     *
     * @param i - количество элементов
     * @return сообщение
     */
    String lessThanOwner(long i);

    /**
     * Выдаёт сообщение был ли элемент заменён или нет
     *
     * @param b - был или нет
     * @return сообщение
     */
    String elementReplaced(boolean b);


}
