package sample.commands;

import sample.UI.TranslatorUIController;
import sample.translator.Translator;

import java.io.IOException;

public class DictionaryCommand implements Command {

    private Translator translator;

    public DictionaryCommand(Translator translator) {
        this.translator = translator;
    }

    @Override
    public void execute() {
        try {
            translator.getFromDictionary("ru", "String");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
