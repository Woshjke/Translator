package response;

import java.util.Arrays;

public class TranslatorResponse {
    private String code;
    private String lang;
    private String[] text;

    public TranslatorResponse() {

    }

    public TranslatorResponse(String code, String lang, String[] text) {
        this.code = code;
        this.lang = lang;
        this.text = text;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String[] getText() {
        return text;
    }

    public void setText(String[] text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "TranslatorResponse {" +
                "Код операции: '" + code + '\'' +
                ", Язык перевода: '" + lang + '\'' +
                ", Переведенный текст: " + Arrays.toString(text) +
                '}';
    }
}
