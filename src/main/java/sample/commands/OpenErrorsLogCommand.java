package sample.commands;

import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.Alert;
import sample.Message;
import sample.translator.Translator;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class OpenErrorsLogCommand implements Command {

    private Translator translator;

    public OpenErrorsLogCommand(Translator translator) {
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
                        try {
                            File errors = new File("error.txt");
                            Desktop.getDesktop().edit(errors);
                        } catch (IOException e) {
                            Platform.runLater(() -> new Message("Cannot open file!", Alert.AlertType.ERROR).show());
                        }
                        return null;
                    }
                };
            }
        };
        backgroundService.restart();
    }
}
