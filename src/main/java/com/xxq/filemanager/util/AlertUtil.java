package com.xxq.filemanager.util;


import com.xxq.filemanager.util.alert.AlertInterface;
import com.xxq.filemanager.util.alert.impl.AlertImpl;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;


public class AlertUtil {

    public static AlertInterface alertInterface = new AlertImpl();

    public static Optional<ButtonType> alert(Alert.AlertType type, String message) {
        return alertInterface.alert(type, message);
    }

    public static Optional<ButtonType> alert(Alert.AlertType type, String message, Stage stage) {
        return alertInterface.alert(type, message, stage);
    }


}
