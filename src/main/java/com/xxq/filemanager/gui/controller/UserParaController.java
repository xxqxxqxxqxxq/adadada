package com.xxq.filemanager.gui.controller;

import com.xxq.filemanager.bean.DepartInfo;
import com.xxq.filemanager.bean.SysUserInfo;
import com.xxq.filemanager.gui.view.UserParaView;
import com.xxq.filemanager.service.interfaceI.DepartService;
import com.xxq.filemanager.springJavafxSupport.FXMLController;
import com.xxq.filemanager.service.interfaceI.UserService;
import com.xxq.filemanager.util.AlertUtil;
import com.xxq.filemanager.util.MailUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.MessagingException;
import java.net.URL;
import java.util.List;
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
    DepartService departService;
    @Autowired
    UserManagerController userManagerController;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    private SysUserInfo userInfo;
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
        userInfo = sysUserInfo;
    }
    @FXML
    public void updateUser() throws MessagingException {
        logger.info("点击更新按钮");
       SysUserInfo sysUserInfo=new SysUserInfo();
        String text = U_Depart.getText();
        setdepart(sysUserInfo,text);
        String passwordText = U_Password.getText();
        sysUserInfo.setId(Integer.valueOf(U_Id.getText().trim()));
        sysUserInfo.setPassword(U_Password.getText());
        sysUserInfo.setRole(U_Role.getText().trim());
        sysUserInfo.setPower(Integer.valueOf(U_Power.getText().trim()));
        System.out.println(sysUserInfo);
        if(!userInfo.getPassword().equals(passwordText)){
            MailUtil.modifyPasswordMailSuccess(userInfo.getEmail(),passwordText);
        }
        userService.updateUser(sysUserInfo);
        userManagerController.userList();
        AlertUtil.alert(Alert.AlertType.INFORMATION,"用户信息修改成功");
        userParaView.hide();
    }
    public void setdepart(SysUserInfo sysUserInfo,String text){
        List<DepartInfo> departInfos = departService.selectAllDepart();
        departInfos.forEach(x->{
            if (x.getDepartName().equals(sysUserInfo.getDepartName())){
                sysUserInfo.setDepartId(x.getId());
            }
        });

    }
}
