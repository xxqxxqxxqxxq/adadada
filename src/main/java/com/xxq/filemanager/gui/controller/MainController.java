package com.xxq.filemanager.gui.controller;

import com.xxq.FileClient;
import com.xxq.filemanager.gui.view.*;
import com.xxq.filemanager.springJavafxSupport.FXMLController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @ClassName MainController
 * @Description: TODO
 * @Author xxq
 * @Date 2020/1/16 14:05
 * @Version V1.0
 **/
@FXMLController
public class MainController implements Initializable {
    private static Logger logger = Logger.getLogger(MainController.class);
    /**
     * 注销按钮
     */
    @FXML
    private Button JB_SignOut;

    /**
     * 登录按钮
     */
    @FXML
    private Button JB_login;

    /**
     * 用户管理
     */
    @FXML
    private Button JB_UserMng;

    /**
     * 个人中心
     */
    @FXML
    private Button JB_PersonMng;


    /**
     * 分类管理
     */
    @FXML
    private Button JB_Kind;
    /**
     * 档案管理
     */
    @FXML
    private Button JB_FileMng;
    /**
     * 借阅管理
     */
    @FXML
    private Button JB_BorrowMng;

    /**
     * 机构管理
     */
    @FXML
    private Button JB_DepartMng;
    /**
     * 点击左侧展示展示
     */
    @FXML
    AnchorPane JP_Show;

    @Autowired
    UserMngView userMngView;
    @Autowired
    LoginView loginView;
    @Autowired
    PersonMngView personMngView;
    @Autowired
    FileMngView fileMngView;
    @Autowired
    PersonManagerController personManagerController;

    @Autowired
    UserManagerController userManagerController;

    public Button getJB_Kind() {
        return JB_Kind;
    }

    public void setJB_Kind(Button JB_Kind) {
        this.JB_Kind = JB_Kind;
    }

    public Button getJB_FileMng() {
        return JB_FileMng;
    }

    public void setJB_FileMng(Button JB_FileMng) {
        this.JB_FileMng = JB_FileMng;
    }

    public Button getJB_BorrowMng() {
        return JB_BorrowMng;
    }

    public void setJB_BorrowMng(Button JB_BorrowMng) {
        this.JB_BorrowMng = JB_BorrowMng;
    }


    public Button getJB_UserMng() {
        return JB_UserMng;
    }

    public void setJB_UserMng(Button JB_UserMng) {
        this.JB_UserMng = JB_UserMng;
    }

    public Button getJB_PersonMng() {
        return JB_PersonMng;
    }

    public void setJB_PersonMng(Button JB_PersonMng) {
        this.JB_PersonMng = JB_PersonMng;
    }

    public Button getJB_DepartMng() {
        return JB_DepartMng;
    }

    public void setJB_DepartMng(Button JB_DepartMng) {
        this.JB_DepartMng = JB_DepartMng;
    }

    public AnchorPane getJP_Show() {
        return JP_Show;
    }

    public void setJP_Show(AnchorPane JP_Show) {
        this.JP_Show = JP_Show;
    }

    public Button getJB_login() {
        return JB_login;
    }

    public void setJB_login(Button JB_login) {
        this.JB_login = JB_login;
    }

    public Button getJB_SignOut() {
        return JB_SignOut;
    }

    public void setJB_SignOut(Button JB_SignOut) {
        this.JB_SignOut = JB_SignOut;
    }

    @FXML
    public void loginout(){
        FileClient.sysUser = null;
        JB_SignOut.setVisible(false);
        JB_login.setVisible(true);
        JB_PersonMng.setVisible(false);
        JB_DepartMng.setVisible(false);
        JB_UserMng.setVisible(false);
        JP_Show.setVisible(false);
        JB_BorrowMng.setVisible(false);
        JB_FileMng.setVisible(false);
        JB_Kind.setVisible(false);
    }
    @FXML
    public void login() {
        FileClient.showView(LoginView.class,   Modality.APPLICATION_MODAL);
    }
    @FXML
    public void regist() {
        FileClient.showView(RegisterUserView.class,Modality.NONE);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void dealUserManager(){
        JP_Show.getChildren().clear();
        JP_Show.getChildren().add(userMngView.getView());
    }
    @FXML
    public void dealPersonMng(){
        JP_Show.getChildren().clear();
        JP_Show.getChildren().add(personMngView.getView());
        personManagerController.showPerson();
    }
    @FXML
    public void dealFileMng(){
        JP_Show.getChildren().clear();
        JP_Show.getChildren().add(fileMngView.getView());
    }


}
