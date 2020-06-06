package com.xxq.filemanager.gui.controller;

import com.xxq.FileClient;
import com.xxq.filemanager.bean.ArchivesInfo;
import com.xxq.filemanager.bean.DepartInfo;
import com.xxq.filemanager.bean.SysUserInfo;
import com.xxq.filemanager.entity.ArchPathEntity;
import com.xxq.filemanager.gui.view.UploadView;
import com.xxq.filemanager.service.interfaceI.ArchivesService;
import com.xxq.filemanager.service.interfaceI.DepartService;
import com.xxq.filemanager.springJavafxSupport.FXMLController;
import com.xxq.filemanager.util.AlertUtil;
import com.xxq.filemanager.util.Constants;
import com.xxq.filemanager.util.OpenPDF;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @ClassName :UploadController
 * @Description:
 * @Author xxq
 * @Date 2020/5/7  13:56
 * @Version V1.0
 **/
@FXMLController
public class UploadController implements Initializable {
    private static Logger logger= Logger.getLogger(UploadController.class);
    @FXML
    private TextField ArchNo;
    @FXML
    private TextField ArchName;
    @FXML
    private TextField Arch_Type;
    @FXML
    private Label L_FilePath;
    @FXML
    private TextField ArchType;
    @FXML
    private ComboBox CMB_DepartName1;
    @Autowired
    ArchivesService archivesService;
    @Autowired
    UploadView uploadView;
    @Autowired
    FileMngController fileMngController;
    @Autowired
    DepartService departService;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CMB_DepartName1.getItems().add(Constants.PLEASE_SELECT);
        List<DepartInfo> departInfos = departService.selectAllDepart();
        for(DepartInfo d: departInfos){
           CMB_DepartName1.getItems().add(d.getDepartName());
        }
    }
    public static  String path;
    @FXML
    public void look() {
        OpenPDF openPDF = new OpenPDF();
         path = openPDF.open();
        L_FilePath.setText(path);
    }
    @FXML
    public void upload() {
        String archName = ArchName.getText();
        String archNo = ArchNo.getText();
        String archTypeText = ArchType.getText();
        String departName = "";
        if(CMB_DepartName1.getSelectionModel().getSelectedItem()!= null) {
            departName = CMB_DepartName1.getSelectionModel().getSelectedItem().toString();
            if (Constants.PLEASE_SELECT.equals(departName)){
                departName = null;
            }
        }

        ArchivesInfo archivesInfo = new ArchivesInfo();
        setdepart(archivesInfo,departName);
        archivesInfo.setArchNo(archNo);
        archivesInfo.setClassId(Integer.valueOf(Arch_Type.getText()));
        archivesInfo.setArchivesName(archName);
        archivesInfo.setCreateBy(FileClient.sysUser.getUsername());
        archivesInfo.setArchivesType(archTypeText);

        archivesService.insertOneFile(archivesInfo);
        ArchPathEntity archPathEntity = new ArchPathEntity();
        archPathEntity.setPath(path);
        archPathEntity.setArchNo(archNo);
        int i = archivesService.insertArchPath(archPathEntity);
        AlertUtil.alert(Alert.AlertType.INFORMATION,"上传成功",FileClient.getStage());
        uploadView.hide();
        fileMngController.fileList();
    }

    public void setdepart(ArchivesInfo archivesInfo, String text){
        switch (text){
            case "党群部门" :
                archivesInfo.setDepartId(1);
                break;
            case "行政部门":
                archivesInfo.setDepartId(2);
                break;
            case "教学单位":
                archivesInfo.setDepartId(3);
                break;
            case "科研单位":
                archivesInfo.setDepartId(4);
                break;
            case "党基层组织":
                archivesInfo.setDepartId(5);
                break;
        }
    }
}
