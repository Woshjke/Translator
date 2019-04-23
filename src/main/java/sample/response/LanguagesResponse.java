package sample.response;

import com.google.gson.annotations.SerializedName;
import sample.response.dictionary.Response;

import java.util.Arrays;
import java.util.Map;

public class LanguagesResponse implements Response {

    @SerializedName(value = "dirs")
    private String[] translationDirections;

    @SerializedName(value = "langs")
    private Map<String,String> languages;

    public LanguagesResponse() {

    }

    public LanguagesResponse(String[] dirs, Map<String, String> langs) {
        this.translationDirections = dirs;
        this.languages = langs;
    }

    public String[] getTranslationDirections() {
        return translationDirections;
    }

    public void setTranslationDirections(String[] translationDirections) {
        this.translationDirections = translationDirections;
    }

    public Map<String, String> getLanguages() {
        return languages;
    }

    public void setLanguages(Map<String, String> languages) {
        this.languages = languages;
    }

    @Override
    public String toString() {
        return "LanguagesResponse{" +
                "translationDirections=" + Arrays.toString(translationDirections) +
                ", languages=" + languages +
                '}';
    }
}
