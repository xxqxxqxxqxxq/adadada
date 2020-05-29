package com.xxq.filemanager.gui.controller;

import com.xxq.FileClient;
import com.xxq.filemanager.bean.ArchivesInfo;
import com.xxq.filemanager.gui.view.*;
import com.xxq.filemanager.springJavafxSupport.FXMLController;
import com.xxq.filemanager.springJavafxSupport.FXMLView;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import sun.applet.Main;

import javax.swing.plaf.nimbus.State;
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
    private MainView mainView;
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
    @FXML
    private Button JB_ShowFile;
    /**
     * 借阅管理
     */
    @FXML
    private Button JB_BorrowMng;
@FXML
private Button JB_regist;
    /**
     * 机构管理
     */
    @FXML
    private Button JB_DepartMng;
    /**
     * 点击左侧展示展示
     */
    @FXML
    StackPane JP_Show;
    @FXML
    AnchorPane A_Gonggao;
    @FXML
    TitledPane T_UserMng;
    @FXML
    TitledPane T_TypMng;
    @FXML
    TitledPane T_PerMng;
    @FXML
    TitledPane T_FileMng;
    @FXML
    TitledPane T_BorMng;
    @FXML
    TitledPane T_DepMng;
    @FXML
    TitledPane T_OtherMng;

    @Autowired
    UserMngView userMngView;

    @Autowired
    LoginView loginView;

    @Autowired
    PersonMngView personMngView;

    @Autowired
    FileMngView fileMngView;

    @Autowired
    BorrowFileView borrowFileView;

    @Autowired
    AdminInfoView adminInfoView;

    @Autowired
    ShowFileView showFileView;
    @Autowired
    MyBorrowView myBorrowView;
    @Autowired
    DepartSearchView departSearchView;
    @Autowired
    UserBorView userBorView;
    @Autowired
    FileTypeView fileTypeView;
    @Autowired
    BorApproveView borApproveView;
    @Autowired
    ModifyTypeView modifyTypeView;
    @Autowired
    UpdateTypeView updateTypeView;
    @Autowired
    OtherView otherView;
    @Autowired
    PersonManagerController personManagerController;

    @Autowired
    AdminController adminController;

    @Autowired
    UserManagerController userManagerController;
    @Autowired
    OtherController otherController;
    @Autowired
    ShowFileController showFileController;
    @Autowired
    MyBorrowController myBorrowController;
    @Autowired
    FileDiviMngController fileDiviMngController;
    @Autowired
    BorApproveController borApproveController;

    public Button getJB_ShowFile() {
        return JB_ShowFile;
    }

    public Button getJB_regist() {
        return JB_regist;
    }

    public void setJB_regist(Button JB_regist) {
        this.JB_regist = JB_regist;
    }

    public void setJB_ShowFile(Button JB_ShowFile) {
        this.JB_ShowFile = JB_ShowFile;
    }

    public Button getJB_Kind() {
        return JB_Kind;
    }

    public TitledPane getT_OtherMng() {
        return T_OtherMng;
    }

    public MainView getMainView() {
        return mainView;
    }

    public void setMainView(MainView mainView) {
        this.mainView = mainView;
    }

    public void setT_OtherMng(TitledPane t_OtherMng) {
        T_OtherMng = t_OtherMng;
    }

    public TitledPane getT_UserMng() {
        return T_UserMng;
    }

    public void setT_UserMng(TitledPane t_UserMng) {
        T_UserMng = t_UserMng;
    }

    public TitledPane getT_TypMng() {
        return T_TypMng;
    }

    public void setT_TypMng(TitledPane t_TypMng) {
        T_TypMng = t_TypMng;
    }

    public TitledPane getT_PerMng() {
        return T_PerMng;
    }

    public void setT_PerMng(TitledPane t_PerMng) {
        T_PerMng = t_PerMng;
    }

    public TitledPane getT_FileMng() {
        return T_FileMng;
    }

    public void setT_FileMng(TitledPane t_FileMng) {
        T_FileMng = t_FileMng;
    }

    public TitledPane getT_BorMng() {
        return T_BorMng;
    }

    public void setT_BorMng(TitledPane t_BorMng) {
        T_BorMng = t_BorMng;
    }

    public TitledPane getT_DepMng() {
        return T_DepMng;
    }

    public void setT_DepMng(TitledPane t_DepMng) {
        T_DepMng = t_DepMng;
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

    public StackPane getJP_Show() {
        return JP_Show;
    }

    public void setJP_Show(StackPane JP_Show) {
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
    public void loginout() {
        JP_Show.getChildren().clear();
        FileClient.sysUser = null;
        JB_SignOut.setVisible(false);
        JB_login.setVisible(true);
        JB_regist.setVisible(true);
        T_UserMng.setVisible(false);
        T_PerMng.setVisible(false);
        T_BorMng.setVisible(false);
        T_FileMng.setVisible(false);
        T_TypMng.setVisible(false);
        T_DepMng.setVisible(false);
        T_OtherMng.setVisible(false);
        JP_Show.setVisible(false);
    }
    @FXML
    private WebView webView;
    @FXML
    public void login() {
        FileClient.showView(LoginView.class, Modality.APPLICATION_MODAL);
    }

    @FXML
    public void regist() {
        FileClient.showView(RegisterUserView.class, Modality.NONE);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        T_UserMng.setVisible(false);
        T_PerMng.setVisible(false);
        T_BorMng.setVisible(false);
        T_FileMng.setVisible(false);
        T_TypMng.setVisible(false);
        T_DepMng.setVisible(false);
        T_OtherMng.setVisible(false);
        JB_SignOut.setVisible(false);
        final WebEngine webengine = webView.getEngine();
        String url = Main.class.getResource("/html/index.html").toExternalForm();
        webengine.load(url);

    }

    @FXML
    public void dealUserManager() {
        JP_Show.getChildren().clear();
        JP_Show.getChildren().add(userMngView.getView());
    }

    @FXML
    public void dealPersonMng() {
        JP_Show.getChildren().clear();
        JP_Show.getChildren().add(personMngView.getView());
        personManagerController.showPerson();
    }

    @FXML
    public void dealFileMng() {
        JP_Show.getChildren().clear();
        JP_Show.getChildren().add(fileMngView.getView());
    }

    @FXML
    public void dealArchBorrow() {
        JP_Show.getChildren().clear();
        JP_Show.getChildren().add(borrowFileView.getView());
    }

    @FXML
    public void dealAdminInfo() {
        JP_Show.getChildren().clear();
        JP_Show.getChildren().add(adminInfoView.getView());
        adminController.showAdmin();
    }
    public  static boolean flag = true;
    public  static boolean fileTypeFlag = true;
    @FXML
    public void showFile() {
        JP_Show.getChildren().clear();
        JP_Show.getChildren().add(showFileView.getView());
        showFileController.showFile();
    }
    @FXML
    public void dealMyBorrow() {
        JP_Show.getChildren().clear();
        JP_Show.getChildren().add(myBorrowView.getView());
        myBorrowController.showMyBorrow();
    }
    @FXML
    public void dealDepartSearch() {
        JP_Show.getChildren().clear();
        JP_Show.getChildren().add(departSearchView.getView());
    }
    @FXML
    public void dealFileType() {
        JP_Show.getChildren().clear();
        JP_Show.getChildren().add(fileTypeView.getView());
        fileDiviMngController.show1();
    }
    @FXML
    public void dealUserBor() {
        JP_Show.getChildren().clear();
        JP_Show.getChildren().add(userBorView.getView());
    }
    @FXML
    public void dealDeleteAndAdd() {
        JP_Show.getChildren().clear();
        JP_Show.getChildren().add(modifyTypeView.getView());

    }
    @FXML
    public void dealOtherMng() {
        JP_Show.getChildren().clear();
        JP_Show.getChildren().add(otherView.getView());
        otherController.lineChart();
    }
}
