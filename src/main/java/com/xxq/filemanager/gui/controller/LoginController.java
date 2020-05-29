package com.xxq.filemanager.gui.controller;

import com.xxq.FileClient;
import com.xxq.filemanager.bean.SysUserInfo;
import com.xxq.filemanager.gui.view.LoginView;
import com.xxq.filemanager.gui.view.MainView;
import com.xxq.filemanager.gui.view.RegisterUserView;
import com.xxq.filemanager.gui.view.UserLoginView;
import com.xxq.filemanager.springJavafxSupport.FXMLController;
import com.xxq.filemanager.service.interfaceI.LoginService;
import com.xxq.filemanager.util.AlertUtil;
import com.xxq.filemanager.util.MailUtil;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.MessagingException;
import java.io.IOException;
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
public class LoginController implements Initializable {

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
    @Autowired
    UserLoginView userLoginView;
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

    private void login() {
        Stage stage = loginView.getStage();
        String username = userName.getText().trim();
        String password = passWord.getText().trim();
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            AlertUtil.alert(Alert.AlertType.WARNING, "用户名或密码不能为空", stage);
            return;
        }
        SysUserInfo userInfo = loginService.login(username, password);

        boolean login = userInfo != null;
        if (login && FileClient.sysUser == null) {
            logger.info("登陆成功");
            passWord.clear();
            FileClient.sysUser=userInfo;
            if (userInfo.getPower()==3){
                mainController.getJB_login().setVisible(false);
                mainController.getJB_SignOut().setVisible(true);
                mainController.getT_BorMng().setVisible(true);
                mainController.getT_DepMng().setVisible(true);
                mainController.getT_FileMng().setVisible(true);
                mainController.getT_TypMng().setVisible(true);
                mainController.getT_UserMng().setVisible(true);
                mainController.getJB_FileMng().setVisible(true);
                mainController.getT_OtherMng().setVisible(true);
                mainController.getJB_ShowFile().setVisible(false);
                mainController.getJB_SignOut().setVisible(true);
                mainController.getJB_regist().setVisible(false);
                mainController.getJP_Show().setVisible(true);
                loginView.getStage().hide();
            }else {
                mainController.getT_PerMng().setVisible(true);
                mainController.getJB_SignOut().setVisible(true);
                mainController.getT_FileMng().setVisible(true);
                mainController.getJB_FileMng().setVisible(false);
                mainController.getT_OtherMng().setVisible(false);
                mainController.getJB_ShowFile().setVisible(true);
                mainController.getJB_SignOut().setVisible(true);
                mainController.getJB_regist().setVisible(false);
                mainController.getJP_Show().setVisible(true);
                mainController.getJP_Show().getChildren().add(userLoginView.getView());
                loginView.hide();
            }

        } else {
            AlertUtil.alert(Alert.AlertType.WARNING, "用户名或密码错误！！！", stage);
        }

    }

    @FXML
    public void sendEmail() throws MessagingException {
        String username = userName.getText();
        MailUtil.sendModifyPasswordMail(username);
        AlertUtil.alert(Alert.AlertType.INFORMATION,"已经通知管理员，请稍后查看邮箱");
    }
}
