package sample.jsonReaders;

import com.google.gson.Gson;
import sample.response.LanguagesResponse;
import sample.response.TranslatorResponse;

public class LangsJsonReader {
    public LanguagesResponse getLanguageResponce(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, LanguagesResponse.class);
    }
}
