package com.xxq.filemanager.gui.controller;

import com.xxq.FileClient;
import com.xxq.filemanager.bean.SysUserInfo;
import com.xxq.filemanager.springJavafxSupport.FXMLController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @ClassName AdminController
 * @Description: TODO
 * @Author xxq
 * @Date 2020/3/22 13:21
 * @Version V1.0
 **/
@FXMLController
public class AdminController implements Initializable {
    @FXML
    private TextField A_Number;
    @FXML
    private TextField A_Username;
    @FXML
    private TextField A_Phone;
    @FXML
    private TextField A_Depart;
    @FXML
    private TextField A_Email;
    @Autowired
    UserParaController userParaController;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        A_Number.setEditable(false);
        A_Username.setEditable(false);
    }

    public void modify(ActionEvent actionEvent) {

    }
    public void showAdmin(){
        SysUserInfo sysUser = FileClient.sysUser;
        A_Number.setText(String.valueOf(sysUser.getId()));
        A_Username.setText(sysUser.getUsername());
        A_Phone.setText(sysUser.getPhone());
        A_Depart.setText(sysUser.getDepartName());
        A_Email.setText(sysUser.getEmail());
    }

}
