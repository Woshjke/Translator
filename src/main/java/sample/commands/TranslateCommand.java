package sample.commands;

import com.google.cloud.translate.TranslateException;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import sample.AppStarter;
import sample.Message;
import sample.response.TranslatorResponse;
import sample.translator.Translator;
import sample.ui.TranslatorUIController;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

public class TranslateCommand implements Command {

    private Translator translator;

    public TranslateCommand(Translator translator) {
        this.translator = translator;
    }

    private void writeLog(String textToTranslate, String translatedText, String sourceLangAbr, String targetLangAbr) {
        File file = new File("log.txt");
        synchronized (file) {
            try {
                boolean writable = file.setWritable(true);
                if (!writable) {
                    Platform.runLater(() -> new Message("Cannot set file writable!", Alert.AlertType.ERROR).show());
                }
                FileOutputStream log = new FileOutputStream(file, true);
                log.write(sourceLangAbr.getBytes());
                log.write(": ".getBytes());
                log.write(textToTranslate.getBytes());
                log.write(" - ".getBytes());
                log.write(targetLangAbr.getBytes());
                log.write(": ".getBytes());
                log.write(translatedText.getBytes());
                log.write("\n".getBytes());
                writable = file.setWritable(false);
                if (!writable) {
                    Platform.runLater(() -> new Message("Cannot set file not writable!", Alert.AlertType.ERROR).show());
                }
                log.close();
            } catch (IOException e) {
                Platform.runLater(() -> new Message(e.getMessage(), Alert.AlertType.ERROR).show());
            }
        }
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
                        TranslatorUIController controller = AppStarter.getLoader().getController();

//                        if (!checkConnection()) {
//                            Platform.runLater(() -> new Message("Check your Internet connection", Alert.AlertType.ERROR).show());
//                            return null;
//                        }

                        try {
                            Map<String, String> langMap = translator.getLanguages().getLanguages();
                            String sourceLanguage = controller.getSourceLanguage().getValue();
                            String targetLanguage = controller.getTargetLanguage().getValue();
                            TextField textToTranslateField = controller.getTextToTranslateField();
                            TextField translatedTextField = controller.getTranslatedTextField();
                            RadioButton yandexRadioButton = controller.getYandexRadioButton();
                            RadioButton googleRadioButton = controller.getGoogleRadioButton();
                            String sourceLangAbr = "";
                            String targetLangAbr = "";

                            for (String iter : langMap.keySet()) {
                                if (langMap.get(iter).equals(sourceLanguage)) {
                                    sourceLangAbr = iter;
                                }
                                if (langMap.get(iter).equals(targetLanguage)) {
                                    targetLangAbr = iter;
                                }
                            }

                            if (yandexRadioButton.isSelected()) {
                                TranslatorResponse response = translator.translateByYandex(textToTranslateField.getText(),
                                        sourceLangAbr, targetLangAbr);
                                translatedTextField.setText(response.getText());
                            } else if (googleRadioButton.isSelected()) {
                                String response = translator.translateByGoogle(textToTranslateField.getText(),
                                        sourceLangAbr, targetLangAbr);
                                translatedTextField.setText(response);
                            }
                            writeLog(textToTranslateField.getText(), translatedTextField.getText(), sourceLangAbr, targetLangAbr);

                        } catch (IOException | TranslateException e) {
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
