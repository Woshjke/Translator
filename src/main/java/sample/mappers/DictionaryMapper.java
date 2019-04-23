package sample.mappers;

import com.google.gson.Gson;
import sample.response.dictionary.DictionaryResponse;
import sample.response.dictionary.Response;

/**
 * Маппит json-объект в класс DictionaryResponse
 */
public class DictionaryMapper implements Mapper {

    @Override
    public Response map(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, DictionaryResponse.class);
    }
}
