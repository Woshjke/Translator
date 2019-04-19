package sample.commands;

import javafx.scene.control.Alert;
import sample.Message;
import sample.response.dictionary.DictionaryResponse;
import sample.translator.Translator;

import java.io.IOException;

public class DictionaryCommand implements Command {

    private Translator translator;

    public DictionaryCommand(Translator translator) {
        this.translator = translator;
    }

    @Override
    public void execute() {

        Runnable runnable = () -> {
            try {
                DictionaryResponse response = translator.getFromDictionary("en-ru", "time");
                new Message(response.toString(), Alert.AlertType.INFORMATION).show();
            } catch (IOException e) {
                new Message(e.getMessage(), Alert.AlertType.ERROR).show();
            }
        };

        //Platform.runLater(runnable);
        runnable.run();
    }
}
