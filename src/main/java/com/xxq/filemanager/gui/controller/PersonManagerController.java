package com.xxq.filemanager.gui.controller;

import com.xxq.FileClient;
import com.xxq.filemanager.bean.SysUserInfo;
import com.xxq.filemanager.springJavafxSupport.FXMLController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @ClassName PersonManagerController
 * @Description: 个人中心
 * @Author xxq
 * @Date 2020/2/11 8:40
 * @Version V1.0
 **/
@FXMLController
public class PersonManagerController implements Initializable {
    @FXML
    private TextField P_Number;
    @FXML
    private TextField P_Phone;
    @FXML
    private TextField P_Username;
    @FXML
    private TextField P_Email;
    @FXML
    private TextField P_Depart;
    @FXML
    private TextField P_Kind;
    @FXML
    private Button P_Modify;
    public TextField getP_Number() {
        return P_Number;
    }

    public void setP_Number(TextField p_Number) {
        P_Number = p_Number;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        P_Number.setEditable(false);
        P_Username.setEditable(false);
        P_Kind.setEditable(false);

    }

    public void showPerson(){
        SysUserInfo userInfo=FileClient.sysUser;
        P_Number.setText(userInfo.getId().toString());
        P_Username.setText(userInfo.getUsername());
        P_Kind.setText(userInfo.getRole());

    }
}
