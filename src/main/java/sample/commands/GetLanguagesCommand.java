package sample.commands;

import javafx.application.Platform;
import javafx.concurrent.Service;
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
        Service<Void> backgroundService = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() {
                        Map<String, String> langMap;
                        TranslatorUIController controller = AppStarter.getLoader().getController();
                        try {
                            langMap = translator.getLanguages().getLangs();
                        } catch (IOException e) {
                            Platform.runLater(() -> new Message(
                                    "Cannot get languages! Check your Internet connection!", Alert.AlertType.ERROR).show()
                            );
                            return null;
                        }
                        langMap.values().forEach(x -> {
                            controller.getSourceLanguage().getItems().add(x);
                            controller.getTargetLanguage().getItems().add(x);
                        });
                        Platform.runLater(() -> {
                            controller.getSourceLanguage().getItems().sort(String::compareTo);
                            controller.getTargetLanguage().getItems().sort(String::compareTo);
                        });
                        controller.setLangMap(langMap);
                        return null;
                    }
                };
            }
        };
        backgroundService.restart();
    }
}
