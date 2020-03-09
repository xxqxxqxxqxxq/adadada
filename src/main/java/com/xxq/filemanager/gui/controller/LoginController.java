package com.xxq.filemanager.gui.controller;

import com.xxq.FileClient;
import com.xxq.filemanager.bean.SysUserInfo;
import com.xxq.filemanager.gui.view.LoginView;
import com.xxq.filemanager.springJavafxSupport.FXMLController;
import com.xxq.filemanager.service.interfaceI.LoginService;
import com.xxq.filemanager.util.AlertUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @ClassName LoginController
 * @Description: 登录管理
 * @Author xxq
 * @Date 2020/1/16 11:25
 * @Version V1.0
 **/
@FXMLController
public class LoginController  implements Initializable{

    private static org.apache.log4j.Logger logger = Logger.getLogger(LoginController.class);
    @Autowired
    LoginView loginView;

    @Autowired
    LoginService loginService;

    @FXML
    private BorderPane ent;
    @FXML
    private TextField userName;
    @FXML
    private PasswordField passWord;
    @FXML
    private RadioButton BT_User;
    @FXML
    private RadioButton BT_Manager;
    @Autowired
    MainController mainController;
    public TextField getUserName() {
        return userName;
    }

    public void setUserName(TextField userName) {
        this.userName = userName;
    }

    public PasswordField getPassword() {
        return passWord;
    }

    public void setPassword(PasswordField password) {
        this.passWord = password;
    }

    public BorderPane getEnt() {
        return ent;
    }

    public void setEnt(BorderPane ent) {
        this.ent = ent;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // 键盘响应事件
//        ent.setOnKeyPressed(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent event) {
//                if (event.getCode() == KeyCode.ENTER) {
//                    login();
//                }
//            }
//        });
    }

    @FXML
    public void loginAction() {
        login();
    }

    private void login(){
        Stage stage=loginView.getStage();
        String username = userName.getText().trim();
        String password = passWord.getText().trim();
        if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            AlertUtil.alert(Alert.AlertType.WARNING,"用户名或密码不能为空",stage);
        }
        SysUserInfo userInfo = loginService.login(username, password);
        boolean login = userInfo !=null;
        if(login&& FileClient.sysUser==null){
            logger.info("登陆成功");
            passWord.clear();
            FileClient.sysUser=userInfo;
            if (userInfo.getPower()==3){
                mainController.getJB_login().setVisible(false);
                mainController.getJB_SignOut().setVisible(true);
                mainController.getJB_UserMng().setVisible(true);
                mainController.getJB_DepartMng().setVisible(true);
                mainController.getJB_BorrowMng().setVisible(true);
                mainController.getJB_FileMng().setVisible(true);
                mainController.getJB_Kind().setVisible(true);
                loginView.getStage().hide();
            }else {
                mainController.getJB_PersonMng().setVisible(true);
                mainController.getJB_SignOut().setVisible(true);
                loginView.hide();
            }

        }else {
            AlertUtil.alert(Alert.AlertType.WARNING, "用户名或密码错误！！！",stage);
        }

    }



}
