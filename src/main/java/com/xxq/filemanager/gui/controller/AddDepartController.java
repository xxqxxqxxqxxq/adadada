package com.xxq.filemanager.gui.controller;

import com.xxq.filemanager.gui.view.AddDepartView;
import com.xxq.filemanager.gui.view.DepartSearchView;
import com.xxq.filemanager.springJavafxSupport.FXMLController;
import com.xxq.filemanager.util.AlertUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @ClassName :AddDepartController
 * @Description:
 * @Author xxq
 * @Date 2020/4/22  17:50
 * @Version V1.0
 **/
@FXMLController
public class AddDepartController implements Initializable {
    @FXML
    private TextField departName;
    @Autowired
    DepartSearchController departSearchController;
    @Autowired
    AddDepartView addDepartView;
    @Autowired
    MainController mainController;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    public void add() {
        String text = departName.getText();
        addNode(text);
        AlertUtil.alert(Alert.AlertType.INFORMATION,"添加成功");
        addDepartView.hide();
    }
    public void addNode(String departName){
        TreeItem<String> item  = (TreeItem<String>) DepartSearchController.map.get("item");
        departSearchController.addOne(departName,item);
    }
}
