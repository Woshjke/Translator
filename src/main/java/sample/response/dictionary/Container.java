package sample.response.dictionary;

public class Container {
    private String text;

    private String num;

    private String pos;

    private String gen;

    public Container() {

    }

    public Container(String text, String num, String pos, String gen) {
        this.text = text;
        this.num = num;
        this.pos = pos;
        this.gen = gen;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public String getGen() {
        return gen;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }

    @Override
    public String toString() {
        String finalText = "";

        if (text != null) {finalText += "\nПример перевода= " + text + "\n";}
        if (num != null) {finalText += "Число = " + num + "\n";}
        if (pos != null) {finalText += "Часть речи= " + pos + "\n";}
        if (gen != null) {finalText += "Род существительного= " + gen + "\n";}
        return  finalText;
    }
}
