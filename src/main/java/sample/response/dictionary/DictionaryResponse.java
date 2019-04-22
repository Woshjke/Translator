package sample.response.dictionary;

public class DictionaryResponse {
    private Object head;

    private DictionaryEntry[] def;


    public DictionaryResponse() {

    }

    public DictionaryResponse(Object head, DictionaryEntry[] def) {
        this.head = head;
        this.def = def;
    }

    public void setHead(Object head) {
        this.head = head;
    }

    public Object getHead() {
        return head;
    }

    public DictionaryEntry[] getDef() {
        return def;
    }

    public void setDef(DictionaryEntry[] def) {
        this.def = def;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Словарная статья:\n");
        for (DictionaryEntry iter : def) {
            stringBuilder.append(iter.toString());
        }
        return stringBuilder.toString();
    }
}
