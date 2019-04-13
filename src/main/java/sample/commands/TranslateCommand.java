package sample.commands;

import sample.Main;
import sample.UI.TranslatorUIController;
import sample.User;
import sample.response.TranslatorResponse;
import sample.translator.Translator;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

public class TranslateCommand implements Command {

    Translator translator;

    public TranslateCommand(Translator translator) {
        this.translator = translator;
    }

    @Override
    public void execute() {
        // TODO: 13.04.2019 Разделить получение языков и перевод
        Map<String, String> langMap;
        TranslatorUIController controller = Main.getLoader().getController();

        try {
            langMap = translator.getLanguages().getLangs();
            langMap.values().forEach(x -> controller.getLanguages().getItems().add(x));
            for (Object o : langMap.keySet()) {
                if (langMap.get(o).equals(controller.getLanguages().getValue())) {
                    TranslatorResponse response = translator.translate(o.toString(), controller.getTextToTranslateField().getText());
                    controller.getTranslatedTextField().setText(response.getText());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
