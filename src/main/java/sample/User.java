package sample;

import sample.commands.Command;

public class User {

    private Command translate;
    private Command getFromDictionary;
    private Command getLanguages;
    private Command openTranslationsLog;
    private Command openErrorsLog;

    public User(Command translate, Command getFromDictionary, Command getLanguages, Command openTranslationsLog, Command openErrorsLog) {
        this.translate = translate;
        this.getFromDictionary = getFromDictionary;
        this.getLanguages = getLanguages;
        this.openTranslationsLog = openTranslationsLog;
        this.openErrorsLog = openErrorsLog;
    }

    public void translateText() {
        translate.execute();
    }

    public void getInfoFromDictionary() {
        getFromDictionary.execute();
    }

    public void getLanguages() {
        getLanguages.execute();
    }

    public void openTranslationsLog() {
        openTranslationsLog.execute();
    }

    public void openErrorsLog() {
        openErrorsLog.execute();
    }
}
