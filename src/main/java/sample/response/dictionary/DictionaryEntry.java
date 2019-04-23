package sample.response.dictionary;

import com.google.gson.annotations.SerializedName;

public class DictionaryEntry {


    @SerializedName(value = "tr")
    private Translation[] translations;

    @SerializedName(value = "syn")
    private Synonym[] synonyms;

    @SerializedName(value = "mean")
    private Mean[] means;

    @SerializedName(value = "ex")
    private Example[] examples;

    public DictionaryEntry() {

    }

    public DictionaryEntry(Translation[] tr, Synonym[] syn, Mean[] mean, Example[] ex) {
        this.translations = tr;
        this.synonyms = syn;
        this.means = mean;
        this.examples = ex;
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
        StringBuilder stringBuilder = new StringBuilder();
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
