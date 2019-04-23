package sample.response;

import com.google.gson.annotations.SerializedName;
import sample.response.dictionary.Response;

import java.util.Arrays;

public class TranslatorResponse implements Response {

    @SerializedName(value = "code")
    private String code;

    @SerializedName(value = "lang")
    private String translationDirection;

    @SerializedName(value = "text")
    private String[] text;

    public TranslatorResponse() {

    }

    public TranslatorResponse(String code, String lang, String[] text) {
        this.code = code;
        this.translationDirection = lang;
        this.text = text;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTranslationDirection() {
        return translationDirection;
    }

    public void setTranslationDirection(String translationDirection) {
        this.translationDirection = translationDirection;
    }

    public String getText() { return Arrays.toString(text).replaceAll("[\\[\\]\"]", ""); }

    public void setText(String[] text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return  "TranslatorResponse {" +
                "Код операции: '" + code + '\'' +
                ", Язык перевода: '" + translationDirection + '\'' +
                ", Переведенный текст: " + getText() +
                '}';
    }
}
