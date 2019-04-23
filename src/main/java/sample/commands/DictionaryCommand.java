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


    private boolean isOneWord(String string) {
        if (string.isEmpty()) {
            return false;
        }
        String[] words = string.split(" ");
        return words.length <= 1;
    }

    private boolean checkConnection() {
        try {
            Process process = java.lang.Runtime.getRuntime().exec("ping www.tut.by");
            return process.waitFor() != 1;
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void execute() {
        Service<Void> backgroundService = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() {

                        if (!checkConnection()) {
                            Platform.runLater(() -> new Message("Check your Internet connection", Alert.AlertType.ERROR).show());
                            return null;
                        }

                        TranslatorUIController controller = AppStarter.getLoader().getController();
                        Map<String, String> langMap = controller.getLangMap();
                        String textToTranslate = controller.getTextToTranslateField().getText();

                        if (!isOneWord(textToTranslate)) {
                            Platform.runLater(() -> new Message("Input one word!", Alert.AlertType.ERROR).show());
                            return null;
                        }

                        String sourceLangAbr = "";
                        String targetLangAbr = "";
                        String selectedSourceLang = controller.getSourceLanguage().getValue();
                        String selectedTargetLang = controller.getTargetLanguage().getValue();
                        for (String iter : langMap.keySet()) {
                            if (langMap.get(iter).equals(selectedSourceLang)) {
                                sourceLangAbr = iter;
                            }
                            if (langMap.get(iter).equals(selectedTargetLang)) {
                                targetLangAbr = iter;
                            }
                        }

                        try {
                            String translationDirection = sourceLangAbr + "-" + targetLangAbr;
                            DictionaryResponse response = translator.getFromDictionary(translationDirection, textToTranslate);
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
