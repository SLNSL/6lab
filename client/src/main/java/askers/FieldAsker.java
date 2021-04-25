package askers;

import checkers.ClientDataChecker;
import data.Color;
import data.UnitOfMeasure;
import printer.Printable;
import messenger.Messenger;

import java.util.Scanner;

/**
 * Класс, который Запрашивает поле поля.
 */
public class FieldAsker implements ClientDataAsker {
    Scanner scanner;
    private boolean scriptMode;
    private Printable printer;
    private Messenger messenger;

    /**
     * Создаёт объект FieldAsker
     *
     * @param scanner       - Сканнер работающий на данный момент
     * @param printer       - вывод информации
     * @param messenger     - возвращает информацию, на языке пользователя
     */
    public FieldAsker(Scanner scanner, Printable printer, Messenger messenger){
        this.scanner = scanner;
        this.scriptMode = false;
        this.printer = printer;
        this.messenger = messenger;
    }


    /**
     * Запрашивает поле ключ для хранения элемента и проверяет введенное значение на корректность
     *
     * @return - key

     */
    public String askKey(){
        printer.println(messenger.askKey());
        Integer key;
        String string;
        string = scanner.nextLine();
        if (scriptMode) printer.println(string);
        if (string == null) return null;
        return string;
    }

    /**
     * Запрашивает поле поле id элемента и проверяет ввод (используется только в команде update) и проверяет введенное значение на корректность
     * @return id

     */
    public String askId(){
        printer.println(messenger.askID());
        String string;
        string = scanner.nextLine();
        if (scriptMode) printer.println(string);
//        id = fielsChecker.checkId(string, willExist);
        return string;
    }

    /**
     * Запрашивает поле name элемента и проверяет правильность введенного значения
     * @return - name

     */
    public String askName(){
        printer.println(messenger.askName());
        String name;
        name = scanner.nextLine();
        if (scriptMode) printer.println(name);
//        name = fielsChecker.checkName(name);
        return name;
    }

    /**
     * Запрашивает поле coordinates.x элемента и проверяет введенное значение на корректность
     * @return - coordinates.x

     */
    public String askCoordinatesX(){
        printer.println(messenger.askCoordinatesX());
        String string = scanner.nextLine();
        if (scriptMode) printer.println(string);
//        x = fielsChecker.checkCoordinatesX(string);
        return string;
    }

    /**
     * Запрашивает поле coordinates.y элемента и проверяет введенное значение на корректность
     * @return - coordinates.y

     */
    public String askCoordinatesY(){
        printer.println(messenger.askCoordinatesY());
        String string = scanner.nextLine();
        if (scriptMode) printer.println(string);
//        y = fielsChecker.checkCoordinatesY(string);
        return string;
    }

    /**
     * Запрашивает поле price элемента и проверяет введенное значение на корректность
     *
     * @return - price

     */
    public String askPrice() {
        double price;
        printer.println(messenger.askPrice());
        String string = scanner.nextLine();
        if (scriptMode) printer.println(string);
//        price = fielsChecker.checkPrice(string);
        return string;
    }

    /**
     * Запрашивает поле partNumber элемента и проверяет введенное значение на корректность
     *
     * @return partNumber

     */
    public String askPartNumber() {
        printer.println(messenger.askPartNumber());
        String partNumber;
        partNumber = scanner.nextLine();
        if (scriptMode) printer.println(partNumber);
//        partNumber = fielsChecker.checkPartNumber(partNumber);
        return partNumber;
    }

    /**
     * Запрашивает поле manufactureCost элемента и проверяет введенное значение на корректность
     *
     * @return manufactureCost

     */
    public String askManufactureCost(){
        double manufactureCost;
        printer.println(messenger.askManufactureCost());
        String string = scanner.nextLine();
        if (scriptMode) printer.println(string);
//        manufactureCost = fielsChecker.checkManufactureCost(string);
        return string;
    }

    /**
     * Запрашивает поле unitOfMeasure элемента и проверяет введенное значение на корректность
     *
     * @return unitOfMeasure

     */
    public String askUnitOfMeasure(){
        UnitOfMeasure unitOfMeasure;
        printer.println(messenger.askUnitOfMeasure());
        String string;
        string = scanner.nextLine();
        if (scriptMode) printer.println(string);
//        unitOfMeasure = fielsChecker.checkUnitOfMeasure(string);
        return string;

    }

    /**
     * Запрашивает поле owner.name элемента и проверяет введенное значение на корректность
     *
     * @return owner.name

     */
    public String askOwnerName() {
        printer.println(messenger.askOwnerName());
        String name = scanner.nextLine();
        if (scriptMode) printer.println(name);
//        name = fielsChecker.checkOwnerName(name);
        return name;
    }

    /**
     * Запрашивает поле owner.passportID элемента и проверяет введенное значение на корректность
     * @return owner.passportID

     */
    public String askOwnerPassportID() {
        printer.println(messenger.askOwnerPassportID());
        String passportId = scanner.nextLine();
        if (scriptMode) printer.println(passportId);
//        passportId = fielsChecker.checkOwnerPassportId(passportId, willExist);
        return passportId;
    }

    /**
     * Запрашивает поле owner.hairColor элемента и проверяет введенное значение на корректность
     *
     * @return owner.hairColor

     */
    public String askOwnerHairColor() {
        printer.println(messenger.askOwnerHairColor());
        Color color;
        String string;
        string = scanner.nextLine();
        if (scriptMode) printer.println(string);
        if (string == null) return null;
//        color = fielsChecker.checkOwnerHairColor(string);
        return string;
    }

    /**
     * Запрашивает owner.location.x элемента и проверяет введенное значение на корректность
     *
     * @return owner.location.x

     */
    public String askOwnerLocationX() {
        printer.println(messenger.askOwnerLocationX());
        long x;
        String string = scanner.nextLine();
        if (scriptMode) printer.println(string);
//        x = fielsChecker.checkOwnerLocationX(string);
        return string;
    }

    /**
     * Запрашивает owner.location.y элемента и проверяет введенное значение на корректность
     *
     * @return owner.location.y

     */
    public String askOwnerLocationY() {
        printer.println(messenger.askOwnerLocationY());
        double y;
        String string = scanner.nextLine();
        if (scriptMode) printer.println(string);
//        y = fielsChecker.checkOwnerLocationY(string);
        return string;
    }

    /**
     * Запрашивает owner.location.z элемента и проверяет введенное значение на корректность
     *
     * @return owner.location.z

     */
    public String askOwnerLocationZ() {
        printer.println(messenger.askOwnerLocationZ());
        Float z;
        String string = scanner.nextLine();
        if (scriptMode) printer.println(string);
//        z = fielsChecker.checkOwnerLocationZ(string);
        return string;
    }

    /**
     * Запрашивает owner.location.name элемента и проверяет введенное значение на корректность
     *
     * @return owner.location.name

     */
    public String askOwnerLocationName() {
        printer.println(messenger.askOwnerLocationName());
        String name;
        name = scanner.nextLine();
        if (scriptMode) printer.println(name);
//        name = fielsChecker.checkOwnerName(name);
        return name;
    }

    /**
     * Устанавливает сканнер(чтение из файла, чтение из консоли)
     *
     * @param scanner
     */
    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public Scanner getScanner() {
        return scanner;
    }

    /**
     * Проверяет происходит чтение команд из скрипта, или из консоли
     *
     * @return
     */
    public boolean isScriptMode() {
        return scriptMode;
    }

    public void setScriptMode(boolean scriptMode) {
        this.scriptMode = scriptMode;
    }

}
