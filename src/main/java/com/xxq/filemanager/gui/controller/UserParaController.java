package com.xxq.filemanager.gui.controller;

import com.xxq.filemanager.bean.SysUserInfo;
import com.xxq.filemanager.gui.view.UserParaView;
import com.xxq.filemanager.springJavafxSupport.FXMLController;
import com.xxq.filemanager.service.interfaceI.UserService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @ClassName UserParaController
 * @Description: 用户参数管理
 * @Author xxq
 * @Date 2020/2/27 13:37
 * @Version V1.0
 **/
@FXMLController
public class UserParaController implements Initializable {
    private Logger logger=Logger.getLogger(UserParaController.class);

    @FXML
    private TextField U_Id;
    @FXML
    private TextField U_Username;
    @FXML
    private TextField U_Password;
    @FXML
    private TextField U_Phone;
    @FXML
    private TextField U_Email;
    @FXML
    private TextField U_Role;
    @FXML
    private TextField U_Power;
    @FXML
    private TextField U_Depart;

    @Autowired
    UserParaView userParaView;
    @Autowired
    UserService userService;
    @Autowired
    UserManagerController userManagerController;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    /**
     * 查看用户详情
     */
    public void showUser(SysUserInfo sysUserInfo){
        U_Id.setText(sysUserInfo.getId().toString());
        U_Id.setEditable(false);
        U_Depart.setText(sysUserInfo.getDepartName());
        U_Email.setText(sysUserInfo.getEmail());
        U_Role.setText(sysUserInfo.getRole());
        U_Password.setText(sysUserInfo.getPassword());
        U_Phone.setText(sysUserInfo.getPhone());
        U_Username.setText(sysUserInfo.getUsername());
        U_Power.setText(sysUserInfo.getPower().toString());
    }
    @FXML
    public void updateUser() {
        logger.info("点击更新按钮");
       SysUserInfo sysUserInfo=new SysUserInfo();
        String text = U_Depart.getText();
        setdepart(sysUserInfo,text);
        sysUserInfo.setId(Integer.valueOf(U_Id.getText().trim()));
        sysUserInfo.setPassword(U_Password.getText().trim());
        sysUserInfo.setRole(U_Role.getText().trim());
        sysUserInfo.setPower(Integer.valueOf(U_Power.getText().trim()));
        System.out.println(sysUserInfo);
        userService.updateUser(sysUserInfo);
        userManagerController.userList();
        userParaView.hide();
    }
    public void setdepart(SysUserInfo sysUserInfo,String text){
        switch (text){
            case "党群部门" :
                sysUserInfo.setDepartId(1);
                break;
            case "行政部门":
                sysUserInfo.setDepartId(2);
                break;
            case "教学单位":
                sysUserInfo.setDepartId(3);
                break;
            case "科研单位":
                sysUserInfo.setDepartId(4);
                break;
            case "党基层组织":
                sysUserInfo.setDepartId(5);
                break;
        }
    }
}
