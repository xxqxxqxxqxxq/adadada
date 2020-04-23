package com.xxq.filemanager.gui.controller;

import com.xxq.FileClient;
import com.xxq.filemanager.entity.ArchTypeEntity;
import com.xxq.filemanager.entity.ArchivesEntity;
import com.xxq.filemanager.service.interfaceI.ArchivesService;
import com.xxq.filemanager.springJavafxSupport.FXMLController;
import com.xxq.filemanager.util.AlertUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @ClassName BorApproveController
 * @Description:
 * @Author xxq
 * @Date 2020/4/3
 * @Version V1.0
 **/
@FXMLController
public class ModifyTypeController implements Initializable {
    @FXML
    private TextField TF_TypeNo;
    @FXML
    private TextField TF_Type;
    @Autowired
    ArchivesService archivesService;
    @Autowired
    FileDiviMngController fileDiviMngController;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    public void addNewType() {
         Integer typeNo =Integer.parseInt(TF_TypeNo.getText()) ;
        ArchTypeEntity archTypeEntity = new ArchTypeEntity();
        archTypeEntity.setTypeNo(typeNo);
        archTypeEntity.setType(TF_Type.getText());
        archTypeEntity.setCreateBy(FileClient.sysUser.getUsername());
        boolean b = archivesService.addOneType(archTypeEntity);
        System.out.println(b);
        if (b){
            AlertUtil.alert(Alert.AlertType.INFORMATION,"添加成功", FileClient.getStage());
        }

    }
    @FXML
    public void deleteOne() {
        Integer typeNo =Integer.parseInt(TF_TypeNo.getText()) ;
        ArchTypeEntity archTypeEntity = new ArchTypeEntity();
        archTypeEntity.setTypeNo(typeNo);
        archTypeEntity.setType(TF_Type.getText());
        archivesService.deleteOneType(archTypeEntity);
        fileDiviMngController.show1();
        AlertUtil.alert(Alert.AlertType.INFORMATION,"删除成功",FileClient.getStage());
    }

}
