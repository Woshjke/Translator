package sample.commands;

import sample.Main;
import sample.UI.TranslatorUIController;
import sample.translator.Translator;

import java.io.IOException;
import java.util.Map;

public class GetLanguagesCommand implements Command {

    Translator translator;

    public GetLanguagesCommand(Translator translator) {
        this.translator = translator;
    }

    @Override
    public void execute() throws IOException {
        Map<String, String> langMap;
        langMap = Main.getTranslator().getLanguages().getLangs();
        TranslatorUIController controller = Main.getLoader().getController();
        langMap.values().forEach(x -> controller.getLanguages().getItems().add(x));
    }
}
