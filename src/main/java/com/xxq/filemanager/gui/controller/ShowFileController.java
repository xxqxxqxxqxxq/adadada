package com.xxq.filemanager.gui.controller;

import com.xxq.FileClient;
import com.xxq.filemanager.bean.ArchivesInfo;
import com.xxq.filemanager.config.ContextConfig;
import com.xxq.filemanager.gui.view.FileView;
import com.xxq.filemanager.gui.view.ShowFileView;
import com.xxq.filemanager.springJavafxSupport.FXMLController;
import com.xxq.filemanager.util.AlertUtil;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * @ClassName ShowFileController
 * @Description: TODO
 * @Author xxq
 * @Date 2020/3/22 18:23
 * @Version V1.0
 **/
@FXMLController
public class ShowFileController implements Initializable {
    private static Logger logger = Logger.getLogger(ShowFileController.class);
    @FXML
    private ScrollPane scPane;

    public ScrollPane getScPane() {
        return scPane;
    }

    public void setScPane(ScrollPane scPane) {
        this.scPane = scPane;
    }

    public static Map<Integer, FileController> filePanes = new HashMap<>();
    public static FlowPane flowPane = new FlowPane();
    static {
        flowPane.setStyle("-fx-background-color: white");
        flowPane.setPadding(new Insets(2));
        flowPane.setVgap(2);
        flowPane.setHgap(2);
        flowPane.setPrefWidth(1000);
        flowPane.setPrefHeight(800);
    }
    @Autowired
    ShowFileView showFileView;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    public void showWarning() {

        AlertUtil.alert(Alert.AlertType.WARNING, "没有访问权限！！！");
    }

    public void showFile(){
        if(MainController.flag){
            List<ArchivesInfo> archvivesList = FileClient.archvivesList;
            for(int i =0 ;i<archvivesList.size();i++){
                addUI(archvivesList.get(i));
            }
            Platform.runLater(()->{
                for (ArchivesInfo a:archvivesList) {
                    FileController fileController = filePanes.get(a.getId());
                    flowPane.getChildren().add(fileController.getFileView().getView());
                }
                scPane.setContent(flowPane);
            });
            MainController.flag = false;
        }


    }
    private static void addUI(ArchivesInfo archivesInfo){
        FileView view = ContextConfig.context.getBean(FileView.class);
        FileController fileController = (FileController) view.getPresenter();
        fileController.setFileView(view);
        filePanes.put(archivesInfo.getId(),fileController);
        Image image = new Image("/photos/file.png");
        try {
            Platform.runLater(()->{
                fileController.getFileImg().setImage(image);
                fileController.getL_Info().setText(archivesInfo.getArchivesName());
                fileController.getL_ArchNo().setText(String.valueOf(archivesInfo.getArchNo()));
            });
        }catch (Exception e){
            logger.error("图片加载失败",e);
        }

    }
}
