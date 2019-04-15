package sample.mappers;

import com.google.gson.Gson;
import sample.response.LanguagesResponse;

public class LangMapper {
    public LanguagesResponse getLanguageResponse(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, LanguagesResponse.class);
    }
}
