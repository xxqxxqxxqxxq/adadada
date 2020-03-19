package com.xxq.filemanager.gui.controller;

import com.xxq.filemanager.bean.ArchivesParaInfo;
import com.xxq.filemanager.service.interfaceI.ArchivesService;
import com.xxq.filemanager.springJavafxSupport.FXMLController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @ClassName FileParaController
 * @Description: TODO
 * @Author xxq
 * @Date 2020/2/25 12:54
 * @Version V1.0
 **/
@FXMLController
public class FileParaController implements Initializable {
    private Logger logger = Logger.getLogger(FileParaController.class);
    @FXML
    private TextField JT_Name;
    @FXML
    private TextField JT_ArchNo;
    @FXML
    private TextField JT_Nation;
    @FXML
    private TextField JT_Gender;
    @FXML
    private TextField JT_Birth;
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
    private TextArea JT_Remark;

    @Autowired
    ArchivesService archivesService;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void showArchPara(Integer archNo){
        ArchivesParaInfo archivesParaInfo = archivesService.queryArchParaByAID(archNo);
        JT_ArchNo.setText(String.valueOf(archNo));
        JT_Name.setText(archivesParaInfo.getName());
        JT_Address.setText(archivesParaInfo.getAddress());
        JT_Birth.setText(String.valueOf(archivesParaInfo.getBirthdate()));
        JT_Email.setText(archivesParaInfo.getEmail());
        JT_IDCard.setText(archivesParaInfo.getIdCard());
        JT_Marital.setText(archivesParaInfo.getMaritalStatus());
        JT_Nation.setText(archivesParaInfo.getNation());
        JT_Phone.setText(archivesParaInfo.getPhone());
        JT_Political.setText(archivesParaInfo.getPolitical());
        JT_Positions.setText(archivesParaInfo.getPositions());
        JT_Remark.setText(archivesParaInfo.getRemark());
        JT_Religion.setText(archivesParaInfo.getReligion());
        JT_Gender.setText(archivesParaInfo.getGender());
    }
}
