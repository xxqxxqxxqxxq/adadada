package com.xxq.filemanager.gui.controller;

import com.xxq.FileClient;
import com.xxq.filemanager.entity.ArchTypeEntity;
import com.xxq.filemanager.entity.ArchivesEntity;
import com.xxq.filemanager.gui.view.FileTypeView;
import com.xxq.filemanager.service.interfaceI.ArchivesService;
import com.xxq.filemanager.service.interfaceI.BorrowService;
import com.xxq.filemanager.springJavafxSupport.FXMLController;
import com.xxq.filemanager.util.AlertUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;
import java.net.URL;
import java.util.List;
import java.util.Optional;
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

    @Autowired
    MainController mainController;
    @Autowired
    FileTypeView fileTypeView;
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
        ArchTypeEntity archTypeEntity1 = archivesService.queryArchType(typeNo);

        if(archTypeEntity1 != null){
            AlertUtil.alert(Alert.AlertType.WARNING,"档案号已存在添加失败", FileClient.getStage());
          return;
        }
        boolean b = archivesService.addOneType(archTypeEntity);
        System.out.println(b);
        if (b){
            AlertUtil.alert(Alert.AlertType.INFORMATION,"添加成功", FileClient.getStage());
        }

    }

    public boolean deleteOne(Integer typeNo) {
        ArchTypeEntity archTypeEntity = new ArchTypeEntity();
        archTypeEntity.setTypeNo(typeNo);

        Optional<ButtonType> alert = AlertUtil.alert(Alert.AlertType.CONFIRMATION, "确定删除该档案号", FileClient.getStage());
        if (alert.get() == ButtonType.OK) {

            boolean b = archivesService.deleteOneType(archTypeEntity);
            if(!b){
                AlertUtil.alert(Alert.AlertType.WARNING, "删除失败");
                return false;
            }
            AlertUtil.alert(Alert.AlertType.INFORMATION,"删除成功",FileClient.getStage());
            return true;
        }
        return false;
    }

}
