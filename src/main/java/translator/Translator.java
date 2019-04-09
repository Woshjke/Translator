package translator;

import jsonReaders.TranslatorJsonReader;
import response.TranslatorResponse;

import javax.net.ssl.HttpsURLConnection;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;

public class Translator {
    //Ссылка на API Яндекс Переводчика
    private final String URL = "https://translate.yandex.net/api/v1.5/tr.json/translate?key=";
    private String key = "trnsl.1.1.20190409T183919Z.e60acd8daff6f806.f569e10ca83dbbcd54d77d8a953032818eca7282";

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

        URL urlObj = new URL(URL + key);
        HttpsURLConnection connection = (HttpsURLConnection) urlObj.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
        dataOutputStream.writeBytes("text=" + URLEncoder.encode(text, "UTF-8") + "&lang=" + lang);

        //Получаем ответ от сервера в виде json-файла
        InputStream response = connection.getInputStream();
        String json = new java.util.Scanner(response).nextLine();

        //Получаем объект TranslatorResponse из json-a и выводим инормацию об объекте на экран
        TranslatorJsonReader reader = new TranslatorJsonReader();

        return reader.getTranslatorResponse(json);
    }
}
