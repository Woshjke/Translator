package sample.response.dictionary;

import com.google.gson.annotations.SerializedName;

public class Synonym {
    @SerializedName(value = "text")
    private String text;

    @SerializedName(value = "num")
    private String number;

    @SerializedName(value = "pos")
    private String partOfSpeech;

    @SerializedName(value = "gen")
    private String generation;

    @SerializedName(value = "tr")
    private Translation[] translations;

    @SerializedName(value = "syn")
    private Synonym[] synonyms;

    @SerializedName(value = "mean")
    private Mean[] means;

    @SerializedName(value = "ex")
    private Example[] examples;

    public Synonym() {

    }

    public Synonym(String text, String num, String pos, String gen, Translation[] tr, Synonym[] syn, Mean[] mean, Example[] ex) {
        this.text = text;
        this.number = num;
        this.partOfSpeech = pos;
        this.generation = gen;
        this.translations = tr;
        this.synonyms = syn;
        this.means = mean;
        this.examples = ex;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    public void setPartOfSpeech(String partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }

    public String getGeneration() {
        return generation;
    }

    public void setGeneration(String generation) {
        this.generation = generation;
    }

    public Translation[] getTranslations() {
        return translations;
    }

    public void setTranslations(Translation[] translations) {
        this.translations = translations;
    }

    public Synonym[] getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(Synonym[] synonyms) {
        this.synonyms = synonyms;
    }

    public Mean[] getMeans() {
        return means;
    }

    public void setMeans(Mean[] means) {
        this.means = means;
    }

    public Example[] getExamples() {
        return examples;
    }

    public void setExamples(Example[] examples) {
        this.examples = examples;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Синоним:\n");
        if (text != null) { stringBuilder.append("\t\tТекст: ").append(text).append("\n"); }
        if (number != null) { stringBuilder.append("\t\tЧисло: ").append(number).append("\n"); }
        if (partOfSpeech != null) { stringBuilder.append("\t\tЧасть речи: ").append(partOfSpeech).append("\n"); }
        if (generation != null) { stringBuilder.append("\t\tРод: ").append(generation).append("\n"); }
        if (translations != null) {
            for (Translation iter : translations) {
                stringBuilder.append(iter);
            }
        }
        if (synonyms != null) {
            for (Synonym iter : synonyms) {
                stringBuilder.append(iter);
            }
        }
        if (means != null) {
            for (Mean iter : means) {
                stringBuilder.append(iter);
            }
        }
        if (examples != null) {
            for (Example iter : examples) {
                stringBuilder.append(iter);
            }
        }
        return stringBuilder.toString();
    }
}
