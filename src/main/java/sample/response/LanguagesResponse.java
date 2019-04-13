package sample.response;

import java.util.Arrays;
import java.util.Map;

public class LanguagesResponse {
    private String[] dirs;

    private Map<String,String> langs;

    public LanguagesResponse() {

    }

    public LanguagesResponse(String[] dirs, Map<String, String> langs) {
        this.dirs = dirs;
        this.langs = langs;
    }

    public String[] getDirs() {
        return dirs;
    }

    public void setDirs(String[] dirs) {
        this.dirs = dirs;
    }

    public Map<String, String> getLangs() {
        return langs;
    }

    public void setLangs(Map<String, String> langs) {
        this.langs = langs;
    }

    @Override
    public String toString() {
        return "LanguagesResponse{" +
                "dirs=" + Arrays.toString(dirs) +
                ", langs=" + langs +
                '}';
    }
}
