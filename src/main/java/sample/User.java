package sample;

import sample.commands.Command;

import java.io.IOException;

public class User {

    private Command translate;
    private Command getFromDictionary;
    private  Command getLanguages;

    public User(Command translate, Command getFromDictionary, Command getLanguages) {
        this.translate = translate;
        this.getFromDictionary = getFromDictionary;
        this.getLanguages = getLanguages;
    }

    public void translateText() throws IOException {
        translate.execute();
    }

    public void getInfoFromDictionary() throws IOException {
        getFromDictionary.execute();
    }

    public void getLanguages() throws IOException {
        getLanguages.execute();
    }
}
