package sample.translator;

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


    public TranslatorResponse translate(String lang, String text) throws IOException {

        URL urlObj = new URL(URL + key);
        HttpsURLConnection connection = (HttpsURLConnection) urlObj.openConnection();

        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
        dataOutputStream.writeBytes("text=" + URLEncoder.encode(text, "UTF-8") + "&lang=" + lang);

        InputStream response = connection.getInputStream();
        String json = new java.util.Scanner(response).nextLine();

        TranslatorMapper reader = new TranslatorMapper();
        return reader.getTranslatorResponse(json);
    }

    public DictionaryResponse getFromDictionary(String lang, String text) throws IOException {

        // TODO: 15.04.2019 Доделать
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

    public LanguagesResponse getLanguages() throws IOException {

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
