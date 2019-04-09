package main.java.translator;

import main.java.jsonReaders.TranslatorJsonReader;
import main.java.response.TranslatorResponse;

import javax.net.ssl.HttpsURLConnection;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

public class Translator {
    //Ссылка на API Яндекс Переводчика
    private static final String URL = "https://translate.yandex.net/api/v1.5/tr.json/translate?key=";
    private static final String key = "trnsl.1.1.20190409T183919Z.e60acd8daff6f806.f569e10ca83dbbcd54d77d8a953032818eca7282";

    public static String translate(String lang, String input) throws IOException {

        URL urlObj = new URL(URL + key);
        HttpsURLConnection connection = (HttpsURLConnection) urlObj.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
        dataOutputStream.writeBytes("text=" + URLEncoder.encode(input, "UTF-8") + "&lang=" + lang);

        //Получаем ответ от сервера в виде json-файла
        InputStream response = connection.getInputStream();
        String json = new java.util.Scanner(response).nextLine();

        //Получаем объект TranslatorResponse из json-a и выводим инормацию об объекте на экран
        TranslatorJsonReader reader = new TranslatorJsonReader();
        TranslatorResponse translatorResponse = reader.getTranslatorResponse(json);
        System.out.println("Ответ от сервера: " + translatorResponse);

        //Получаем из json-a переведенную строку.
        int start = json.indexOf("[");
        int end = json.indexOf("]");
        String translated = json.substring(start + 2, end - 1);
        if (translated.equals(input)) {
            // если возвращается неперееденная строка, то меняем направление перевода
            return translate("en", input);
        } else {
            return translated;
        }
    }
}