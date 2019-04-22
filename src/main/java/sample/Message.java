package sample;

import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class Message {

    private String content;
    private Alert.AlertType alertType;

    public Message(String content, Alert.AlertType alertType) {

        this.content = content;
        this.alertType = alertType;
    }

    private void writeErrorsLog(String content) {
        try {
            File file = new File("error.txt");
            FileOutputStream fileOutputStream = new FileOutputStream(file, true);
            fileOutputStream.write("Error: ".getBytes());
            fileOutputStream.write(content.getBytes());
            fileOutputStream.write("\n".getBytes());
            fileOutputStream.close();
        } catch (IOException e) {
            new Message(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    public void show() {
        TextArea textArea = new TextArea(content);
        textArea.setStyle("-fx-font-size: 15px");
        textArea.setEditable(false);
        textArea.setWrapText(true);
        GridPane gridPane = new GridPane();
        gridPane.setMaxWidth(Double.MAX_VALUE);
        gridPane.add(textArea, 0, 0);
        Alert alert = new Alert(alertType);
        alert.getDialogPane().setContent(gridPane);

        switch (alertType) {
            case ERROR:
                alert.setHeaderText("Ошибка!");
                alert.getDialogPane().setStyle("-fx-background-color: #ff2e2e; -fx-border-width: 0; -fx-border-color: #771111");
                break;
            case WARNING:
                alert.setHeaderText("Внимание!");
                alert.getDialogPane().setStyle("-fx-background-color: #ffffdd; -fx-border-width: 0; -fx-border-color: #555555");
                break;
            case INFORMATION:
                alert.setHeaderText("");
                alert.getDialogPane().setStyle("-fx-background-color: #ffffff; -fx-border-width: 0; -fx-border-color: #999999");
                break;
        }
        alert.show();
        writeErrorsLog(content);
    }
}