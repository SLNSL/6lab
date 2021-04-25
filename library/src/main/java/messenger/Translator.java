package messenger;

import wrappers.FieldResult;
import wrappers.Result;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Класс ответственный за установку и проверку языка.
 */
public class Translator {
    private String language;
    private LinkedHashMap<String, Messenger> availableLanguages = new LinkedHashMap<>();

    {
        availableLanguages.put("rus", new MessengerRu());
        availableLanguages.put("eng", new MessengerEng());
    }

    /**
     * Спрашивает какой язык будет использовать пользователь
     *
     * @return - Класс Messenger с ведённым языком
     */
    public Result<Messenger> setLanguage(String language) {
        Result<Messenger> result = new FieldResult<>();
        try {
            language = language.trim();
            if (!language.isEmpty()) {
                if ((!getAvailableLanguages().containsKey(language))) {
                    result.setError("Поле language введено неверно! / Field language entered incorrectly!");
                    return result;
                } else {
                    result.setResult(availableLanguages.get(language));
                    return result;
                }
            }
        } catch (NoSuchElementException e) {
            return result;
        }

        result.setError("Ожидался ввод");
        return result;
    }


    /**
     * gives map of available languages
     *
     * @return map of available languages
     */
    public LinkedHashMap<String, Messenger> getAvailableLanguages() {
        return availableLanguages;
    }

    /**
     * gives language on which the user is working
     *
     * @return language
     */
    public String getLanguage() {
        return language;
    }
}
