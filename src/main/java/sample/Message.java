package sample;

import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

public class Message {
    private Alert alert;
    private String content;
    private Alert.AlertType alertType;

    public Alert getAlert() {
        return alert;
    }

    public void setAlert(Alert alert) {
        this.alert = alert;
    }

    public Message(String content, Alert.AlertType alertType) {

        TextArea textArea = new TextArea(content);
        textArea.setEditable(false);
        textArea.setWrapText(true);
        GridPane gridPane = new GridPane();
        gridPane.setMaxWidth(Double.MAX_VALUE);
        gridPane.add(textArea, 0, 0);
        alert = new Alert(alertType);
        alert.getDialogPane().setContent(gridPane);
        alert.setX(AppStarter.getRoot().getScene().getWindow().getX() + 50);
        alert.setY(AppStarter.getRoot().getScene().getWindow().getY() + 50);


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
    }

    public void show() {
        alert.showAndWait();
    }
}
