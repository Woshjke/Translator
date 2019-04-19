package sample.response.dictionary;

public class Container {
    private String text;

    private String num;

    private String pos;

    private String gen;

    private Container[] tr;

    private Container[] syn;

    private Container[] mean;

    private Container[] ex;

    public Container() {

    }

    public Container(String text, String num, String pos,
                     String gen, Container[] tr, Container[] syn, Container[] mean, Container[] ex) {
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

        if (text != null) {
            finalStr.append("Текст - " + text + "\n");
        }
        if (num != null) {
            finalStr.append("Число - " + num + "\n");
        }
        if (pos != null) {
            finalStr.append("Часть речи - " + pos + "\n");
        }
        if (gen != null) {
            finalStr.append("Род сущиствительного - " + gen  + "\n");
        }
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
