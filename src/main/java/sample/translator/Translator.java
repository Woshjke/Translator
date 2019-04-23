package sample.translator;

import com.google.cloud.translate.Language;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import sample.Message;
import sample.mappers.DictionaryMapper;
import sample.mappers.LangMapper;
import sample.mappers.TranslatorMapper;
import sample.response.LanguagesResponse;
import sample.response.TranslatorResponse;
import sample.response.dictionary.DictionaryResponse;

import javax.net.ssl.HttpsURLConnection;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

/**
 * Класс-receiver. Занимается отправкой запросов на сервера и получением ответа.
 */
public class Translator implements TranslatorInterface {

    private final String TRANSLATOR_URL = "https://translate.yandex.net/api/v1.5/tr.json/translate?";
    private final String TRANSLATOR_KEY = "key=trnsl.1.1.20190409T183919Z.e60acd8daff6f806.f569e10ca83dbbcd54d77d8a953032818eca7282";
    private final String DICTIONARY_KEY = "key=dict.1.1.20190410T165952Z.f8e90fde03f36468.a3eb7708da1dc060f4d5706dd97034c47d0344c0";
    private final String DICTIONARY_URL = "https://dictionary.yandex.net/api/v1/dicservice.json/lookup?";
    private final String GET_LANGUAGE_URL = "https://translate.yandex.net/api/v1.5/tr.json/getLangs?";


    public String getTRANSLATOR_URL() {
        return TRANSLATOR_URL;
    }

    public String getTRANSLATOR_KEY() {
        return TRANSLATOR_KEY;
    }

    public String getDICTIONARY_KEY() {
        return DICTIONARY_KEY;
    }

    public String getDICTIONARY_URL() {
        return DICTIONARY_URL;
    }

    public String getGET_LANGUAGE_URL() {
        return GET_LANGUAGE_URL;
    }

    /**
     *
     * @param text - текст для перевода
     * @param sourceLanguage - язык, с какого переводится текст
     * @param targetLanguage - язык, нак какой переводится текст
     * @return запапленный класс с ответом от сервера
     * @throws IOException - ошибки ввода-вывода
     */
    public TranslatorResponse translateByYandex(String text, String sourceLanguage, String targetLanguage)
            throws IOException {

        if (text.isEmpty()) {
            Platform.runLater(() -> new Message("Empty text field. Write something!", Alert.AlertType.ERROR).show());
            return null;
        }

        URL urlObj = new URL(TRANSLATOR_URL + TRANSLATOR_KEY);
        HttpsURLConnection connection = (HttpsURLConnection) urlObj.openConnection();

        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
        dataOutputStream.writeBytes("text=" + URLEncoder.encode(text, "UTF-8") + "&lang=" + sourceLanguage + "-" + targetLanguage);

        InputStream response = connection.getInputStream();
        String json = new java.util.Scanner(response).nextLine();

        TranslatorMapper reader = new TranslatorMapper();
        return (TranslatorResponse) reader.map(json);
    }

    /**
     *
     * @param text - текст для перевода
     * @param sourceLanguage - язык, с какого переводится текст
     * @param targetLanguage - язык, нак какой переводится текст
     * @return - строку с переведенным текстом
     * @throws com.google.cloud.translate.TranslateException - ошибка при переводе
     */
    public String translateByGoogle (String text, String sourceLanguage, String targetLanguage)
            throws com.google.cloud.translate.TranslateException {

        if (text.isEmpty()) {
            Platform.runLater(() -> new Message("Empty text field. Write something!", Alert.AlertType.ERROR).show());
            return null;
        }

        Translate translate = TranslateOptions.getDefaultInstance().getService();
        List<Language> languages = translate.listSupportedLanguages();

        Translation translation =
                translate.translate(
                        text,
                        Translate.TranslateOption.sourceLanguage(sourceLanguage),
                        Translate.TranslateOption.targetLanguage(targetLanguage)
                );
        return translation.getTranslatedText();
    }

    /**
     *
     * @param lang - язык, на который переводим текст
     * @param text - текст, который переводим
     * @return - запапленный класс с ответом от сервера
     * @throws IOException - ошибки ввода-вывода
     */
    public DictionaryResponse getFromDictionary(String lang, String text)
            throws IOException {

        if (text.isEmpty()) {
            new Message("Empty text field. Write something!", Alert.AlertType.ERROR);
            return null;
        }

        URL dictionaryUrlObj = new URL(DICTIONARY_URL + DICTIONARY_KEY);
        HttpsURLConnection dictionaryConnection = (HttpsURLConnection) dictionaryUrlObj.openConnection();

        dictionaryConnection.setRequestMethod("POST");
        dictionaryConnection.setDoOutput(true);

        DataOutputStream dataOutputStream = new DataOutputStream(dictionaryConnection.getOutputStream());
        dataOutputStream.writeBytes("&lang=" + lang + "&text=" + URLEncoder.encode(text, "UTF-8") + "&ui=ru");

        InputStream response = dictionaryConnection.getInputStream();
        String json = new java.util.Scanner(response).nextLine();
        DictionaryMapper reader = new DictionaryMapper();
        return (DictionaryResponse) reader.map(json);
    }

    /**
     *
     * @return - запапленный класс с ответом от сервера
     * @throws IOException - ошибка ввода-вывода
     */
    public LanguagesResponse getLanguages()
            throws IOException {

        URL urlObj = new URL(GET_LANGUAGE_URL + TRANSLATOR_KEY);
        HttpsURLConnection connection = (HttpsURLConnection) urlObj.openConnection();

        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
        dataOutputStream.writeBytes("&ui=ru");

        InputStream response = connection.getInputStream();
        String json = new java.util.Scanner(response).nextLine();

        LangMapper reader = new LangMapper();
        return (LanguagesResponse) reader.map(json);
    }
}
