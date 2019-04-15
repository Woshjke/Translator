package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sample.commands.DictionaryCommand;
import sample.commands.GetLanguagesCommand;
import sample.commands.TranslateCommand;
import sample.translator.Translator;

import java.io.IOException;

public class Main extends Application {

    private static Translator translator = new Translator();

    private static User user = new User(
            new TranslateCommand(translator),
            new DictionaryCommand(translator),
            new GetLanguagesCommand(translator)
    );

    private static FXMLLoader loader;

    public static Translator getTranslator() {
        return translator;
    }

    public static User getUser() {
        return user;
    }

    public static FXMLLoader getLoader() {
        return loader;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            loader = new FXMLLoader(getClass().getResource("/translatorUI.fxml"));
            Parent root = loader.load();
            primaryStage.setTitle("Translator");
            primaryStage.getIcons().add(new Image("icon.png"));
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.show();
        } catch (IOException e) {
            new Message(e.getMessage(), Alert.AlertType.ERROR).show();
        }
    }
}
