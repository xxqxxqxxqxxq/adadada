package com.xxq.filemanager.gui.controller;

import com.xxq.FileClient;
import com.xxq.filemanager.bean.ArchivesInfo;
import com.xxq.filemanager.bean.ArchivesParaInfo;
import com.xxq.filemanager.bean.DepartInfo;
import com.xxq.filemanager.bean.SysUserInfo;
import com.xxq.filemanager.gui.view.FileAddView;
import com.xxq.filemanager.service.interfaceI.ArchivesService;
import com.xxq.filemanager.springJavafxSupport.FXMLController;
import com.xxq.filemanager.util.AlertUtil;
import com.xxq.filemanager.util.Constants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @ClassName FileAddController
 * @Description: TODO
 * @Author xxq
 * @Date 2020/2/14 10:51
 * @Version V1.0
 **/
@FXMLController
public class FileAddController implements Initializable {
    private Logger logger = Logger.getLogger(FileAddController.class);
    @FXML
    private TextField JT_Name;
    @FXML
    private TextField JT_ArchNo;
    @FXML
    private TextField JT_Nation;
    @FXML
    private TextField JT_Gender;
    @FXML
    private DatePicker JT_Birth;
    @FXML
    private TextField JT_Email;
    @FXML
    private TextField JT_Address;
    @FXML
    private TextField JT_Religion;
    @FXML
    private TextField JT_Positions;
    @FXML
    private TextField JT_Political;
    @FXML
    private TextField JT_Phone;
    @FXML
    private TextField JT_Marital;
    @FXML
    private TextField JT_IDCard;
    @FXML
    private ComboBox C_CDepart;
    @FXML
    private TextField JT_ArchivesName;
    @FXML
    private TextField JT_CreateBy;
    @FXML
    private TextField JT_ArchivesType;
    @FXML
    private TextField JT_ClassId;

    @Autowired
    FileMngController fileMngController;
    @Autowired
    ArchivesService archivesService;
    @Autowired
    FileAddView fileAddView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        C_CDepart.getItems().add(Constants.PLEASE_SELECT);
        for(DepartInfo d: FileClient.departInfoList){
            C_CDepart.getItems().add(d.getDepartName());
        }
    }
    @FXML
    public void addFile() {
        ArchivesInfo archivesInfo = new ArchivesInfo();
        String departName = null;
        if(C_CDepart.getSelectionModel().getSelectedItem()!= null){
            departName = C_CDepart.getSelectionModel().getSelectedItem().toString();
            // 设置机构名称
            setdepart(archivesInfo,departName);
            if (Constants.PLEASE_SELECT.equals(departName)){
                departName = null;
            }
        }
       archivesInfo.setArchNo(Integer.valueOf(JT_ArchNo.getText()));
        archivesInfo.setArchivesName(JT_ArchivesName.getText());
        archivesInfo.setCreateBy(JT_CreateBy.getText());
        archivesInfo.setArchivesType(JT_ArchivesType.getText());
        archivesInfo.setClassId(Integer.valueOf(JT_ClassId.getText()));
        archivesService.insertOneFile(archivesInfo);

        ArchivesParaInfo archivesParaInfo = new ArchivesParaInfo();
        archivesParaInfo.setArchivesId(Integer.valueOf(JT_ArchNo.getText()));
        archivesParaInfo.setName(JT_Name.getText());
        archivesParaInfo.setGender(JT_Gender.getText());
        archivesParaInfo.setNation(JT_Nation.getText());
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
        Date birthdate = new Date();
        try {
            birthdate = sdf.parse(JT_Birth.getValue().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        archivesParaInfo.setBirthdate(birthdate);
        archivesParaInfo.setMaritalStatus(JT_Marital.getText());
        archivesParaInfo.setEmail(JT_Email.getText());
        archivesParaInfo.setPhone(JT_Phone.getText());
        archivesParaInfo.setAddress(JT_Address.getText());
        archivesParaInfo.setPolitical(JT_Political.getText());
        archivesParaInfo.setReligion(JT_Religion.getText());
        archivesParaInfo.setPositions(JT_Positions.getText());
        archivesParaInfo.setIdCard(JT_IDCard.getText());
        archivesParaInfo.setCreateBy(JT_CreateBy.getText());
        archivesService.insertOneFilePara(archivesParaInfo);
        AlertUtil.alert(Alert.AlertType.INFORMATION,"档案添加成功",FileClient.getStage());
       fileAddView.hide();
        logger.info("注册成功！");
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
