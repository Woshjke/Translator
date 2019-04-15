package sample;

import javafx.scene.control.Alert;
import sample.commands.Command;

import java.io.IOException;

public class User {

    private Command translate;
    private Command getFromDictionary;
    private Command getLanguages;

    public User(Command translate, Command getFromDictionary, Command getLanguages) {
        this.translate = translate;
        this.getFromDictionary = getFromDictionary;
        this.getLanguages = getLanguages;
    }

    public void translateText() {
        try {
            translate.execute();
        } catch (IOException e) {
            new Message(e.getMessage(), Alert.AlertType.ERROR).show();
        }
    }

    public void getInfoFromDictionary() {
        try {
            getFromDictionary.execute();
        } catch (IOException e) {
            new Message(e.getMessage(), Alert.AlertType.ERROR).show();
        }
    }

    public void getLanguages() {
        try {
            getLanguages.execute();
        } catch (IOException e) {
            new Message(e.getMessage(), Alert.AlertType.ERROR).show();
        }
    }
}
