package com.xxq.filemanager.util.alert;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;

public interface AlertInterface {

    Optional<ButtonType> alert(Alert.AlertType type, String message);

    Optional<ButtonType> alert(Alert.AlertType type, String message, Stage stage);

    default Optional<ButtonType> alertCommon(Alert.AlertType type, String message, Alert alert) {
        if (null != type) {
            switch (type) {
                case INFORMATION:
                    alert.setTitle("Information");
                    break;
                case WARNING:
                    alert.setTitle("Warning");
                    break;
                case CONFIRMATION:
                    alert.setTitle("Confirmation");
                    break;
            }
        }
        alert.setHeaderText(null);

        alert.setContentText(message);


        return alert.showAndWait();

    }
}
