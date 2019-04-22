package sample.response.dictionary;

public class DictionaryResponse {
    private Object head;

    private Def[] def;


    public DictionaryResponse() {

    }

    public DictionaryResponse(Object head, Def[] def) {
        this.head = head;
        this.def = def;
    }

    public Object getHead() {
        return head;
    }

    public void setHead(Object head) {
        this.head = head;
    }

    public Def[] getDef() {
        return def;
    }

    public void setDef(Def[] def) {
        this.def = def;
    }

    @Override
    public String toString() {
        return def[0].toString();
    }
}
