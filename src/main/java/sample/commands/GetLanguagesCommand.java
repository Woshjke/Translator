package sample.commands;

import javafx.scene.control.Alert;
import sample.Main;
import sample.Message;
import sample.ui.TranslatorUIController;
import sample.translator.Translator;

import java.io.IOException;
import java.util.Map;

public class GetLanguagesCommand implements Command {

    private Translator translator;

    public GetLanguagesCommand(Translator translator) {
        this.translator = translator;
    }

    @Override
    public void execute() {
        Map<String, String> langMap;
        TranslatorUIController controller = Main.getLoader().getController();
        try {
            langMap = Main.getTranslator().getLanguages().getLangs();
            langMap.values().forEach(x -> controller.getLanguages().getItems().add(x));
            controller.setLangMap(langMap);
        } catch (IOException e) {
            new Message(e.getMessage(), Alert.AlertType.ERROR).show();
        }
    }
}
