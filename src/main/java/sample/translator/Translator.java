package sample.translator;

import com.google.cloud.translate.Language;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
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

public class Translator {

    private final String URL = "https://translate.yandex.net/api/v1.5/tr.json/translate?";
    private String key = "key=trnsl.1.1.20190409T183919Z.e60acd8daff6f806.f569e10ca83dbbcd54d77d8a953032818eca7282";

    public Translator() {

    }

    public Translator(String key) {
        this.key = key;
    }

    public String getURL() {
        return URL;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }


    public TranslatorResponse translateByYandex(String text, String sourceLanguage, String targetLanguage)
            throws IOException {

        if (text.isEmpty()) {
            new Message("Empty text field. Write something!", Alert.AlertType.ERROR);
            return null;
        }

        URL urlObj = new URL(URL + key);
        HttpsURLConnection connection = (HttpsURLConnection) urlObj.openConnection();

        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
        dataOutputStream.writeBytes("text=" + URLEncoder.encode(text, "UTF-8") + "&lang=" + sourceLanguage + "-" + targetLanguage);

        InputStream response = connection.getInputStream();
        String json = new java.util.Scanner(response).nextLine();

        TranslatorMapper reader = new TranslatorMapper();
        return reader.getTranslatorResponse(json);
    }

    public String translateByGoogle (String text, String sourceLanguage, String targetLanguage)
            throws com.google.cloud.translate.TranslateException {

        if (text.isEmpty()) {
            new Message("Empty text field. Write something!", Alert.AlertType.ERROR);
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

    public DictionaryResponse getFromDictionary(String lang, String text)
            throws IOException {

        if (text.isEmpty()) {
            new Message("Empty text field. Write something!", Alert.AlertType.ERROR);
            return null;
        }

        String dictionaryURL = "https://dictionary.yandex.net/api/v1/dicservice.json/lookup?key=dict.1.1." +
                "20190410T165952Z.f8e90fde03f36468.a3eb7708da1dc060f4d5706dd97034c47d0344c0";
        URL dictionaryUrlObj = new URL(dictionaryURL);
        HttpsURLConnection dictionaryConnection = (HttpsURLConnection) dictionaryUrlObj.openConnection();

        dictionaryConnection.setRequestMethod("POST");
        dictionaryConnection.setDoOutput(true);

        DataOutputStream dataOutputStream = new DataOutputStream(dictionaryConnection.getOutputStream());
        dataOutputStream.writeBytes("&lang=" + lang + "&text=" + URLEncoder.encode(text, "UTF-8") + "&ui=ru");

        InputStream response = dictionaryConnection.getInputStream();
        String json = new java.util.Scanner(response).nextLine();
        DictionaryMapper reader = new DictionaryMapper();
        return reader.getDictionaryResponse(json);
    }

    public LanguagesResponse getLanguages()
            throws IOException {

        String LangURL = "https://translate.yandex.net/api/v1.5/tr.json/getLangs?";
        URL urlObj = new URL(LangURL + key);
        HttpsURLConnection connection = (HttpsURLConnection) urlObj.openConnection();

        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
        dataOutputStream.writeBytes("&ui=ru");

        InputStream response = connection.getInputStream();
        String json = new java.util.Scanner(response).nextLine();

        LangMapper reader = new LangMapper();
        return reader.getLanguageResponse(json);
    }
}
