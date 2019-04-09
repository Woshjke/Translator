package main.java.jsonReaders;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import main.java.response.TranslatorResponse;

import java.util.List;

public class TranslatorJsonReader {
    public TranslatorResponse getTranslatorResponse(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, TranslatorResponse.class);
    }
}
