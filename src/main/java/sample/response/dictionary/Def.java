package sample.response.dictionary;

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
        StringBuilder finalStr = new StringBuilder();
        finalStr.append("def:\n");
        if (tr != null) {
            for (Container value : tr) {
                if (value != null) {
                    finalStr.append(value.toString() + "\n");
                }
            }
        }
        if (syn != null) {
            for (Container value : syn) {
                if (value != null) {
                    finalStr.append(value.toString() + "\n");
                }
            }
        }
        if (mean != null) {
            for (Container value : mean) {
                if (value != null) {
                    finalStr.append(value.toString() + "\n");
                }
            }
        }
        if (ex != null) {
            for (Container value : ex) {
                if (value != null) {
                    finalStr.append(value.toString() + "\n");
                }
            }
        }

        return finalStr.toString();
    }
}
