package sample.translator;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import sample.Main;
import sample.UI.TranslatorUIController;
import sample.jsonReaders.LangsJsonReader;
import sample.jsonReaders.TranslatorJsonReader;
import sample.response.LanguagesResponse;
import sample.response.TranslatorResponse;

import javax.net.ssl.HttpsURLConnection;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

public class Translator {
    //Ссылка на API Яндекс Переводчика
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

    public TranslatorResponse translate(String lang, String text) throws IOException {

        // TODO: 12.04.2019 ЧУДО
        TranslatorUIController controller = Main.getLoader().getController();
        URL urlObj = new URL(URL + key);
        HttpsURLConnection connection = (HttpsURLConnection) urlObj.openConnection();

        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
        dataOutputStream.writeBytes("text=" + URLEncoder.encode(text, "UTF-8") + "&lang=" + lang);

        //Получаем ответ от сервера в виде json-файла
        InputStream response = connection.getInputStream();
        String json = new java.util.Scanner(response).nextLine();

        //Получаем объект TranslatorResponse из json-TranslatorUI и выводим инормацию об объекте на экран
        TranslatorJsonReader reader = new TranslatorJsonReader();

        return reader.getTranslatorResponse(json);
    }

    public String getFromDictionary (String lang, String text) throws IOException {
        // ключ - dict.1.1.20190410T165952Z.f8e90fde03f36468.a3eb7708da1dc060f4d5706dd97034c47d0344c0

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

        return json;
    }

    public LanguagesResponse getLanguages() throws IOException {
        String LangURL = "https://translate.yandex.net/api/v1.5/tr.json/getLangs?";

        URL urlObj = new URL(LangURL + key);
        HttpsURLConnection connection = (HttpsURLConnection) urlObj.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
        dataOutputStream.writeBytes("&ui=ru");

        //Получаем ответ от сервера в виде json-файла
        InputStream response = connection.getInputStream();
        String json = new java.util.Scanner(response).nextLine();

        //Получаем объект TranslatorResponse из json-TranslatorUI и выводим инормацию об объекте на экран
        LangsJsonReader reader = new LangsJsonReader();

        return reader.getLanguageResponce(json);
    }
}
