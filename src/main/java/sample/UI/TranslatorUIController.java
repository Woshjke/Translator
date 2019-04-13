package sample.UI;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import sample.Main;
import sample.User;
import sample.response.TranslatorResponse;
import sample.translator.Translator;


public class TranslatorUIController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button translateButton;

    @FXML
    private TextField textToTranslateField;

    @FXML
    private TextField translatedTextField;

    @FXML
    private ComboBox<String> languages;


    public Button getTranslateButton() {
        return translateButton;
    }

    public TextField getTextToTranslateField() {
        return textToTranslateField;
    }

    public TextField getTranslatedTextField() {
        return translatedTextField;
    }

    public ComboBox<String> getLanguages() {
        return languages;
    }

    public void setTranslateButton(Button translateButton) {
        this.translateButton = translateButton;
    }

    public void setTextToTranslateField(TextField textToTranslateField) {
        this.textToTranslateField = textToTranslateField;
    }

    public void setTranslatedTextField(TextField translatedTextField) {
        this.translatedTextField = translatedTextField;
    }

    public void setLanguages(ComboBox<String> languages) {
        this.languages = languages;
    }

    @FXML
    void initialize() throws IOException {
        assert translateButton != null : "fx:id=\"translateButton\" was not injected: check your FXML file 'sample.fxml'.";
        assert textToTranslateField != null : "fx:id=\"textToTranslateField\" was not injected: check your FXML file 'sample.fxml'.";
        assert translatedTextField != null : "fx:id=\"translatedTextField\" was not injected: check your FXML file 'sample.fxml'.";
        assert languages != null : "fx:id=\"languages\" was not injected: check your FXML file 'translatorUI.fxml'.";
        Main.getUser().getLanguages();

        translateButton.setOnAction(event -> {
            try {
                Main.getUser().translateText();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
