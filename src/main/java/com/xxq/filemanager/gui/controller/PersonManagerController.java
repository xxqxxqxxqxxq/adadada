package com.xxq.filemanager.gui.controller;

import com.xxq.FileClient;
import com.xxq.filemanager.bean.SysUserInfo;
import com.xxq.filemanager.entity.SysUserEntity;
import com.xxq.filemanager.service.interfaceI.UserService;
import com.xxq.filemanager.springJavafxSupport.FXMLController;
import com.xxq.filemanager.util.AlertUtil;
import com.xxq.filemanager.util.OpenPDF;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.springframework.beans.factory.annotation.Autowired;

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
    private ImageView photo;
    @FXML
    private TextField P_Email;
    @FXML
    private TextField P_Depart;
    @FXML
    private TextField P_Kind;
    @FXML
    private Button P_Modify;
    @Autowired
    UserService userService;
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
        P_Depart.setEditable(false);
    }

    public void showPerson(){
        SysUserInfo userInfo=FileClient.sysUser;
        P_Number.setText(userInfo.getId().toString());
        P_Username.setText(userInfo.getUsername());
        P_Kind.setText(userInfo.getRole());
        P_Phone.setText(userInfo.getPhone());
        P_Email.setText(userInfo.getEmail());
        P_Depart.setText(userInfo.getDepartName());
        String str = userInfo.getPhoto();
//        String photos = str.substring(str.indexOf("photos") + 1);
//        System.out.println(photos);
//        String imagePath = "p"+photos;
        String imagePath = "file:"+str;
        photo.setImage(new Image(imagePath));


    }
    @FXML
    public void modify() {
        String email = P_Email.getText();
        String phone = P_Phone.getText();
        SysUserEntity sysUserEntity = new SysUserEntity();
        sysUserEntity.setId(FileClient.sysUser.getId());
        sysUserEntity.setPhone(phone);
        sysUserEntity.setEmail(email);
        userService.updateUserInfo(sysUserEntity);
        AlertUtil.alert(Alert.AlertType.INFORMATION,"更新成功",FileClient.getStage());
    }
    @FXML
    public void updatePhoto() {
        OpenPDF openPDF = new OpenPDF();
         path = openPDF.open();

    }
    private static String path ;
    @FXML
    public void confirm() {
        SysUserEntity sysUserEntity = new SysUserEntity();
        sysUserEntity.setPhoto(path);
        sysUserEntity.setId(FileClient.sysUser.getId());
        userService.updatePhoto(sysUserEntity);
        String imagePath = "file:"+path;
        photo.setImage(new Image(imagePath));
        AlertUtil.alert(Alert.AlertType.INFORMATION,"头像更换成功",FileClient.getStage());
    }
}
