package sample.commands;

import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.Alert;
import sample.AppStarter;
import sample.Message;
import sample.response.dictionary.DictionaryResponse;
import sample.translator.Translator;
import sample.ui.TranslatorUIController;

import java.io.IOException;
import java.util.Map;

public class DictionaryCommand implements Command {

    private Translator translator;

    public DictionaryCommand(Translator translator) {
        this.translator = translator;
    }

    private boolean oneWordChecker(String string) {
        if (string.isEmpty()) {
            return false;
        }
        String[] words = string.split(" ");
        return words.length <= 1;
    }

    @Override
    public void execute() {
        Service<Void> backgroundService = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() {
                        TranslatorUIController controller = AppStarter.getLoader().getController();
                        Map<String, String> langMap = controller.getLangMap();
                        String sourceLangAbr = "";
                        String targetLangAbr = "";
                        String textToTranslate = controller.getTextToTranslateField().getText();

                        if (!oneWordChecker(textToTranslate)) {
                            Platform.runLater(() -> new Message("Input one word!", Alert.AlertType.ERROR).show());
                            return null;
                        }

                        for (String iter : langMap.keySet()) {
                            if (langMap.get(iter).equals(controller.getSourceLanguage().getValue())) {
                                sourceLangAbr = iter;
                            }
                            if (langMap.get(iter).equals(controller.getTargetLanguage().getValue())) {
                                targetLangAbr = iter;
                            }
                        }

                        try {
                            String lang = sourceLangAbr + "-" + targetLangAbr;
                            DictionaryResponse response = translator.getFromDictionary(lang, textToTranslate);
                            Platform.runLater(() -> new Message(response.toString(), Alert.AlertType.INFORMATION).show());
                        } catch (IOException e) {
                            Platform.runLater(() -> new Message(e.getMessage(), Alert.AlertType.ERROR).show());
                        }
                        return null;
                    }
                };
            }
        };
        backgroundService.restart();
    }
}
