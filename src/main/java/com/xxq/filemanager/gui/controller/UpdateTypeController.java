package com.xxq.filemanager.gui.controller;

import com.xxq.filemanager.entity.ArchTypeEntity;
import com.xxq.filemanager.entity.ArchivesEntity;
import com.xxq.filemanager.gui.view.FileTypeView;
import com.xxq.filemanager.service.interfaceI.ArchivesService;
import com.xxq.filemanager.springJavafxSupport.FXMLController;
import com.xxq.filemanager.util.AlertUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @ClassName :UpdateTypeController
 * @Description:
 * @Author xxq
 * @Date 2020/4/7  19:14
 * @Version V1.0
 **/
@FXMLController
public class UpdateTypeController implements Initializable {
    @FXML
    private TextField TF_TypeNo;
    @FXML
    private TextField TF_Type;
    @FXML
    private TextField TF_NType;
    @Autowired
    ArchivesService archivesService;
    @Autowired
    MainController mainController;
    @Autowired
    FileDiviMngController fileDiviMngController;
    @Autowired
    FileTypeView fileTypeView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    public void updateType() {
        Integer typeNo =Integer.parseInt(TF_TypeNo.getText()) ;
        ArchTypeEntity archTypeEntity = new ArchTypeEntity();
        archTypeEntity.setTypeNo(typeNo);
        archTypeEntity.setType(TF_NType.getText());
        archivesService.updateOneType(archTypeEntity);
        AlertUtil.alert(Alert.AlertType.INFORMATION,"修改成功");
    }
    @FXML
    public void back() {
        mainController.getJP_Show().getChildren().clear();
        mainController.getJP_Show().getChildren().add(fileTypeView.getView());
        fileDiviMngController.show1();
    }

    public void show(Integer typeNo,String type) {
      TF_TypeNo.setText(typeNo.toString());
      TF_Type.setText(type);
    }
}
