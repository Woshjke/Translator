package sample.response.dictionary;

public class DictionaryEntry {

    private Translation[] tr;
    private Synonym[] syn;
    private Mean[] mean;
    private Example[] ex;

    public DictionaryEntry() {

    }

    public DictionaryEntry(Translation[] tr, Synonym[] syn, Mean[] mean, Example[] ex) {
        this.tr = tr;
        this.syn = syn;
        this.mean = mean;
        this.ex = ex;
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
        StringBuilder stringBuilder = new StringBuilder();
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
