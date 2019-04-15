package sample.commands;

import com.sun.xml.internal.ws.api.pipe.ContentType;
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
        try {
            DictionaryResponse response = translator.getFromDictionary("ru-ru", "Привет");
            System.out.println(response);
        } catch (IOException e) {
            new Message(e.getMessage(), Alert.AlertType.ERROR).show();
        }
    }
}
