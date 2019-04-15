package sample.commands;

import javafx.scene.control.Alert;
import sample.Main;
import sample.Message;
import sample.ui.TranslatorUIController;
import sample.response.TranslatorResponse;
import sample.translator.Translator;

import java.io.IOException;
import java.util.Map;

public class TranslateCommand implements Command {

    private Translator translator;

    public TranslateCommand(Translator translator) {
        this.translator = translator;
    }

    @Override
    public void execute() {
        // TODO: 13.04.2019 Разделить получение языков и перевод
        TranslatorUIController controller = Main.getLoader().getController();

        try {
            Map<String, String> langMap = translator.getLanguages().getLangs();
            langMap.values().forEach(x -> controller.getLanguages().getItems().add(x));

            for (String iter : langMap.keySet()) {
                if (langMap.get(iter).equals(controller.getLanguages().getValue())) {
                    TranslatorResponse response = translator.translate(iter, controller.getTextToTranslateField().getText());
                    controller.getTranslatedTextField().setText(response.getText());
                }
            }
        } catch (IOException e) {
            new Message(e.getMessage(), Alert.AlertType.ERROR).show();
        }
    }
}
