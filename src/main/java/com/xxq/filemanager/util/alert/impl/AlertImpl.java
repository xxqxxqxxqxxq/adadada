package com.xxq.filemanager.util.alert.impl;


import com.xxq.filemanager.util.alert.AlertInterface;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;

public class AlertImpl implements AlertInterface {


    @Override
    public Optional<ButtonType> alert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        return alertCommon(type, message, alert);

    }

    @Override
    public Optional<ButtonType> alert(Alert.AlertType type, String message, Stage stage) {
        Alert alert = new Alert(type);
        alert.initOwner(stage);
        return alertCommon(type, message, alert);

    }
}
