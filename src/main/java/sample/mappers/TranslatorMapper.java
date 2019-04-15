package sample.mappers;

import com.google.gson.Gson;
import sample.response.TranslatorResponse;

public class TranslatorMapper {
    public TranslatorResponse getTranslatorResponse(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, TranslatorResponse.class);
    }
}
