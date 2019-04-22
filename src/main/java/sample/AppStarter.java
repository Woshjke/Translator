package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sample.commands.*;
import sample.translator.Translator;

import java.io.IOException;

public class AppStarter extends Application {

    private static Translator translator = new Translator();

    private static User user = new User(
            new TranslateCommand(translator),
            new DictionaryCommand(translator),
            new GetLanguagesCommand(translator),
            new OpenTranslationsLogCommand(translator),
            new OpenErrorsLogCommand(translator)
    );

    private static FXMLLoader loader;

    private static Parent root;

    public static Translator getTranslator() {
        return translator;
    }

    public static User getUser() {
        return user;
    }

    public static FXMLLoader getLoader() {
        return loader;
    }

    public static Parent getRoot() {
        return root;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            loader = new FXMLLoader(getClass().getResource("/translatorUI.fxml"));
            root = loader.load();
            primaryStage.setTitle("Translator");
            primaryStage.getIcons().add(new Image("icon.png"));
            primaryStage.setScene(new Scene(root, 659, 469));
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println("Cannot start program!" + e.getMessage());
        }
    }
}
