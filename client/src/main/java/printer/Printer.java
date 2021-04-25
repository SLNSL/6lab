package printer;

public class Printer implements Printable {

    /**
     * Выводит сообщение
     *
     * @param object сообщение
     */
    @Override
    public void print(Object object) {
        System.out.print(object);
    }

    /**
     * Выводит сообщение с переносом на следующую строку
     *
     * @param object - сообщение
     */
    @Override
    public void println(Object object) {
        System.out.println(object);
    }

    /**
     * Выводит сообщение ошибки
     *
     * @param object - сообщение
     */
    @Override
    public void printlnError(Object object) {
        System.out.println(object);
    }

}
