package sample.commands;

import com.google.cloud.translate.TranslateException;
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

    @Override
    public void execute() {
        Runnable runnable = () -> {
            TranslatorUIController controller = AppStarter.getLoader().getController();

            try {
                Map<String, String> langMap = translator.getLanguages().getLangs();
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
                    TranslatorResponse response = translator.translateByYandex(textToTranslateField.getText(), sourceLangAbr, targetLangAbr);
                    translatedTextField.setText(response.getText());
                } else if (googleRadioButton.isSelected()) {
                    String response = translator.translateByGoogle(textToTranslateField.getText(), sourceLangAbr, targetLangAbr);
                    translatedTextField.setText(response);
                }

                //write in log-file
                File file = new File("log.txt");
                file.setWritable(true);
                FileOutputStream log = new FileOutputStream(file, true);
                log.write(sourceLangAbr.getBytes());
                log.write(": ".getBytes());
                log.write(textToTranslateField.getText().getBytes());
                log.write(" - ".getBytes());
                log.write(targetLangAbr.getBytes());
                log.write(": ".getBytes());
                log.write(translatedTextField.getText().getBytes());
                log.write("\n".getBytes());
                file.setWritable(false);
                log.close();
            } catch (IOException | TranslateException e) {
                new Message(e.getMessage(), Alert.AlertType.ERROR).show();
            }
        };

        runnable.run();
    }
}
