package sample.response.dictionary;

public class Translation {
    private String text;
    private String num;
    private String pos;
    private String gen;

    private Translation[] tr;
    private Synonym[] syn;
    private Mean[] mean;
    private Example[] ex;

    public Translation() {

    }

    public Translation(String text, String num, String pos, String gen, Translation[] tr, Synonym[] syn, Mean[] mean, Example[] ex) {
        this.text = text;
        this.num = num;
        this.pos = pos;
        this.gen = gen;
        this.tr = tr;
        this.syn = syn;
        this.mean = mean;
        this.ex = ex;
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

    public Translation[] getTr() {
        return tr;
    }

    public void setTr(Translation[] tr) {
        this.tr = tr;
    }

    public Synonym[] getSyn() {
        return syn;
    }

    public void setSyn(Synonym[] syn) {
        this.syn = syn;
    }

    public Mean[] getMean() {
        return mean;
    }

    public void setMean(Mean[] mean) {
        this.mean = mean;
    }

    public Example[] getEx() {
        return ex;
    }

    public void setEx(Example[] ex) {
        this.ex = ex;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Перевод:\n");
        if (text != null) { stringBuilder.append("\t\tТекст: ").append(text).append("\n"); }
        if (num != null) { stringBuilder.append("\t\tЧисло: ").append(num).append("\n"); }
        if (pos != null) { stringBuilder.append("\t\tЧасть речи: ").append(pos).append("\n"); }
        if (gen != null) { stringBuilder.append("\t\tРод: ").append(gen).append("\n"); }
        if (tr != null) {
            for (Translation iter : tr) {
                stringBuilder.append(iter);
            }
        }
        if (syn != null) {
            for (Synonym iter : syn) {
                stringBuilder.append(iter);
            }
        }
        if (mean != null) {
            for (Mean iter : mean) {
                stringBuilder.append(iter);
            }
        }
        if (ex != null) {
            for (Example iter : ex) {
                stringBuilder.append(iter);
            }
        }
        return stringBuilder.toString();
    }
}
