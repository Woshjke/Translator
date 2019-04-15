package sample.mappers;

import com.google.gson.Gson;
import sample.response.dictionary.DictionaryResponse;

public class DictionaryMapper {
    public DictionaryResponse getDictionaryResponse(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, DictionaryResponse.class);
    }
}
