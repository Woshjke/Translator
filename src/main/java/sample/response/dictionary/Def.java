package sample.response.dictionary;

import java.util.Arrays;

public class Def {


    private Container[] tr;

    private Container[] syn;

    private Container[] mean;

    private Container[] ex;

    public Def() {

    }

    public Def(Container[] tr, Container[] syn, Container[] mean, Container[] ex) {
        this.tr = tr;
        this.syn = syn;
        this.mean = mean;
        this.ex = ex;
    }

    public Container[] getTr() {
        return tr;
    }

    public void setTr(Container[] tr) {
        this.tr = tr;
    }

    public Container[] getSyn() {
        return syn;
    }

    public void setSyn(Container[] syn) {
        this.syn = syn;
    }

    public Container[] getMean() {
        return mean;
    }

    public void setMean(Container[] mean) {
        this.mean = mean;
    }

    public Container[] getEx() {
        return ex;
    }

    public void setEx(Container[] ex) {
        this.ex = ex;
    }

    @Override
    public String toString() {

        String finalText = "";

        if (tr != null) {finalText += Arrays.toString(tr);}
        if (syn != null) {finalText += Arrays.toString(syn);}
        if (mean != null) {finalText += Arrays.toString(mean);}
        if (ex != null) {finalText += Arrays.toString(ex);}
        return  finalText;
    }
}
