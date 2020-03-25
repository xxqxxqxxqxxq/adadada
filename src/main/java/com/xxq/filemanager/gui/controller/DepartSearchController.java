package com.xxq.filemanager.gui.controller;

import com.xxq.filemanager.springJavafxSupport.FXMLController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @ClassName DepartSearchController
 * @Description: TODO
 * @Author xxq
 * @Date 2020/3/25 10:02
 * @Version V1.0
 **/
@FXMLController
public class DepartSearchController implements Initializable {
    @FXML
    private TextField TF_Search;
    @Autowired
    FileParaController fileParaController;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    public void btnSearch() {
        System.out.println(11);
        fileParaController.showArchPara(2);
    }
}
