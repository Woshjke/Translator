package sample.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import sample.AppStarter;
import sample.Message;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;


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
    private ComboBox<String> sourceLanguage;

    @FXML
    private ComboBox<String> targetLanguage;

    @FXML
    private Button dictionaryButton;

    @FXML
    private RadioButton yandexRadioButton;

    @FXML
    private RadioButton googleRadioButton;

    @FXML
    private Button openLogFileButton;

    private Map<String, String> langMap;


    public Button getTranslateButton() {
        return translateButton;
    }

    public TextField getTextToTranslateField() {
        return textToTranslateField;
    }

    public TextField getTranslatedTextField() {
        return translatedTextField;
    }


    public Map<String, String> getLangMap() {
        return langMap;
    }

    public void setLangMap(Map<String, String> langMap) {
        this.langMap = langMap;
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

    public ComboBox<String> getSourceLanguage() {
        return sourceLanguage;
    }

    public void setSourceLanguage(ComboBox<String> sourceLanguage) {
        this.sourceLanguage = sourceLanguage;
    }

    public ComboBox<String> getTargetLanguage() {
        return targetLanguage;
    }

    public void setTargetLanguage(ComboBox<String> targetLanguage) {
        this.targetLanguage = targetLanguage;
    }

    public RadioButton getYandexRadioButton() {
        return yandexRadioButton;
    }

    public void setYandexRadioButton(RadioButton yandexRadioButton) {
        this.yandexRadioButton = yandexRadioButton;
    }

    public RadioButton getGoogleRadioButton() {
        return googleRadioButton;
    }

    public void setGoogleRadioButton(RadioButton googleRadioButton) {
        this.googleRadioButton = googleRadioButton;
    }

    @FXML
    void initialize() {
        translateButton.setOnAction(event -> AppStarter.getUser().translateText());
        dictionaryButton.setOnAction(event -> AppStarter.getUser().getInfoFromDictionary());
        googleRadioButton.setOnAction(event -> yandexRadioButton.setSelected(false));
        yandexRadioButton.setOnAction(event -> googleRadioButton.setSelected(false));
        yandexRadioButton.setSelected(true);
        openLogFileButton.setOnAction(event -> {
            try {
                File log = new File("log.txt");
                Desktop.getDesktop().edit(log);
            } catch (IOException e) {
                new Message("Cannot open log file", Alert.AlertType.ERROR);
            }
        });
        sourceLanguage.setValue("Русский");
        targetLanguage.setValue("Английский");
    }
}
