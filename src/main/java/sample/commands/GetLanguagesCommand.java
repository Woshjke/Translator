package sample.commands;

import javafx.concurrent.Task;
import javafx.scene.control.Alert;
import sample.AppStarter;
import sample.Message;
import sample.translator.Translator;
import sample.ui.TranslatorUIController;

import java.io.IOException;
import java.util.Map;

public class GetLanguagesCommand implements Command {

    private Translator translator;

    public GetLanguagesCommand(Translator translator) {
        this.translator = translator;
    }

    @Override
    public void execute() {

        Runnable runnable = () -> {
            Map<String, String> langMap;
            TranslatorUIController controller = AppStarter.getLoader().getController();

            try {
                langMap = translator.getLanguages().getLangs();
                langMap.values().forEach(x -> {
                    controller.getSourceLanguage().getItems().add(x);
                    controller.getTargetLanguage().getItems().add(x);
                });
                controller.getSourceLanguage().getItems().sort(String::compareTo);
                controller.getTargetLanguage().getItems().sort(String::compareTo);
                controller.setLangMap(langMap);
            } catch (IOException e) {
                new Message(e.getMessage(), Alert.AlertType.ERROR).show();
            }
        };

        runnable.run();
    }
}
