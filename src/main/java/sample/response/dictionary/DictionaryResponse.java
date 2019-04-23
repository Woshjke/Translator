package sample.response.dictionary;

import com.google.gson.annotations.SerializedName;

public class DictionaryResponse implements Response {

    @SerializedName(value = "head")
    private Object head;

    @SerializedName(value = "def")
    private DictionaryEntry[] dictionaryEntries;


    public DictionaryResponse() {

    }

    public DictionaryResponse(Object head, DictionaryEntry[] dictionaryEntries) {
        this.head = head;
        this.dictionaryEntries = dictionaryEntries;
    }


    public void setHead(Object head) {
        this.head = head;
    }

    public Object getHead() {
        return head;
    }

    public DictionaryEntry[] getDictionaryEntries() {
        return dictionaryEntries;
    }

    public void setDictionaryEntries(DictionaryEntry[] dictionaryEntries) {
        this.dictionaryEntries = dictionaryEntries;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Словарная статья:\n");
        for (DictionaryEntry iter : dictionaryEntries) {
            stringBuilder.append(iter.toString());
        }
        return stringBuilder.toString();
    }
}
