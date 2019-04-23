package sample.mappers;

import com.google.gson.Gson;
import sample.response.LanguagesResponse;
import sample.response.dictionary.Response;

/**
 * Маппит json-объект в класс LanguagesResponse
 */
public class LangMapper implements Mapper {

    @Override
    public Response map(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, LanguagesResponse.class);
    }
}
