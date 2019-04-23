package sample.mappers;

import com.google.gson.Gson;
import sample.response.TranslatorResponse;
import sample.response.dictionary.Response;

/**
 * Маппит json-объект в класс TranslatorResponse
 */
public class TranslatorMapper implements Mapper{

    @Override
    public Response map(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, TranslatorResponse.class);
    }
}
