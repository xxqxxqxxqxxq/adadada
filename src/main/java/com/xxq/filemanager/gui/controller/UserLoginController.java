package com.xxq.filemanager.gui.controller;

import com.xxq.filemanager.gui.view.MyBorrowView;
import com.xxq.filemanager.gui.view.PersonMngView;
import com.xxq.filemanager.gui.view.ShowFileView;
import com.xxq.filemanager.gui.view.UserBorView;
import com.xxq.filemanager.springJavafxSupport.FXMLController;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.effect.DisplacementMap;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @ClassName :UserLoginController
 * @Description:
 * @Author xxq
 * @Date 2020/5/6  11:33
 * @Version V1.0
 **/
@FXMLController
public class UserLoginController implements Initializable {

    @FXML
    private StackPane stackPane;
    @FXML
    private HBox hBox1;
    @FXML
    private HBox hBox2;
    //图片宽度
    private Double width = 200.0;
    @Autowired
    MainController mainController;
    @Autowired
    ShowFileView showFileView;
    @Autowired
    ShowFileController showFileController;
    @Autowired
    MyBorrowController myBorrowController;
    @Autowired
    MyBorrowView myBorrowView;
    @Autowired
    PersonManagerController personManagerController;
    @Autowired
    PersonMngView personMngView;
    @Autowired
    UserBorView userBorView;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        next();
    }

    public void next(){
        HBox topHBox = (HBox) stackPane.getChildren().get(1);
        HBox bottomHBox = (HBox) stackPane.getChildren().get(0);
        Pane pane = new Pane();



        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setDuration(Duration.seconds(6));//切换时间
        translateTransition.setNode(pane);
        translateTransition.setFromX(0);
        translateTransition.setToX(width);
        translateTransition.setInterpolator(Interpolator.LINEAR);//切换方式，这里是线性
        translateTransition.setCycleCount(Animation.INDEFINITE);//自动循环

        DisplacementMap displacementMapTop = new DisplacementMap();
        DisplacementMap displacementMapBottom = new DisplacementMap();
        topHBox.setEffect(displacementMapTop);
        bottomHBox.setEffect(displacementMapBottom);

        //控制HBox的显示
        pane.translateXProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldNumber, Number newNumber) {
                displacementMapTop.setOffsetX(- (newNumber.doubleValue() / width));
                displacementMapBottom.setOffsetX(1 - (newNumber.doubleValue() / width));
            }
        });

        //完成移动动画后将底部一层的HBox置top
        translateTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                bottomHBox.toFront();
            }
        });
        translateTransition.play();
    }
    @FXML
    public void fileMng() {
        mainController.getJP_Show().getChildren().clear();
        mainController.getJP_Show().getChildren().add(showFileView.getView());
        showFileController.showFile();
    }
    @FXML
    public void borReq() {
        mainController.getJP_Show().getChildren().clear();
        mainController.getJP_Show().getChildren().add(userBorView.getView());
    }
    @FXML
    public void myBor() {
        mainController.getJP_Show().getChildren().clear();
        mainController.getJP_Show().getChildren().add(myBorrowView.getView());
        myBorrowController.showMyBorrow();
    }
    @FXML
    public void myInfo() {
        mainController.getJP_Show().getChildren().clear();
        mainController.getJP_Show().getChildren().add(personMngView.getView());
        personManagerController.showPerson();
    }
}
