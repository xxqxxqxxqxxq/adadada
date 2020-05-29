package com.xxq.filemanager.gui.controller;

import com.xxq.filemanager.bean.ArchivesInfo;
import com.xxq.filemanager.entity.ArchTypeEntity;
import com.xxq.filemanager.entity.FileTypeEntity;
import com.xxq.filemanager.gui.view.FileTypeParaView;
import com.xxq.filemanager.gui.view.UpdateTypeView;
import com.xxq.filemanager.service.interfaceI.ArchivesService;
import com.xxq.filemanager.springJavafxSupport.FXMLController;
import com.xxq.filemanager.util.AlertUtil;
import com.xxq.filemanager.util.DateUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import org.springframework.beans.factory.annotation.Autowired;


import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @ClassName FileDiviMngController
 * @Description: 分类管理
 * @Author xxq
 * @Date 2020/2/19 15:24
 * @Version V1.0
 **/
@FXMLController
public class FileDiviMngController implements Initializable {
    @FXML
    private FlowPane add;
    @Autowired
    FileTypeParaController fileTypeParaController;
    @Autowired
    ArchivesService archivesService;
    @Autowired
    FileTypeParaView fileTypeParaView;
    @Autowired
    MainController mainController;
    @Autowired
    UpdateTypeView updateTypeView;
    @Autowired
    UpdateTypeController updateTypeController;
    @Autowired
    ModifyTypeController modifyTypeController;
    private static final int MAX_THREADS = 8;
    //线程池配置
    private final Executor exec = Executors.newFixedThreadPool(MAX_THREADS, runnable -> {
        Thread t = new Thread(runnable);
        t.setDaemon(true);
        return t;
    });

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    public void show(){
        List<ArchTypeEntity> fileTypeEntities = archivesService.queryAllType();
        for(int i =0;i<fileTypeEntities.size();i++){
            Integer id = fileTypeEntities.get(i).getTypeNo();
            Button button = new Button();
            button.setPrefHeight(30);
            button.setPrefWidth(30);
            button.setLayoutX(i*100);
            button.setStyle("-fx-color: #edfffa");
            double height = button.getHeight();
            Image btnImg = new Image("/photos/depart.png");
            ImageView imageView = new ImageView(btnImg);
            imageView.setFitHeight(30);
            imageView.setFitWidth(40);
            //给按钮设置图标
            button.setGraphic(imageView);
            Label label = new Label(""+id);
            if(i > 0){
                label.setLayoutY(height+40);
                label.setLayoutX(i*102);
            }else {
                label.setLayoutY(40);
            }

            add.getChildren().add(button);
            add.getChildren().add(label);
            button.setOnAction(
                    actionEvent-> showPara(id)
            );
        }
    }
    public void show1(){
            add.getChildren().clear();
            List<ArchTypeEntity> fileTypeEntities = archivesService.queryAllType();

            for(ArchTypeEntity archTypeEntity:fileTypeEntities) {
                HBox hBox = new HBox();
                hBox.setPrefSize(300, 240);
                hBox.getStyleClass().add("box");
                hBox.setSpacing(30);
                VBox leftBox = new VBox();
                leftBox.setAlignment(Pos.TOP_CENTER);
                leftBox.setSpacing(30);
                //左边垂直布局放头像和身份
                //开启一个线程，用来读取来自网络的头像
            ImageView imageView = new ImageView();
//            //利用线程池来加载图片，并设置为读者头像
            exec.execute(new Runnable() {
                @Override
                public void run() {
                    imageView.setImage(new Image("/photos/depart.png"));
                }
            });
            imageView.setFitWidth(80);
            imageView.setFitHeight(80);
            Circle circle = new Circle();
            circle.setCenterX(40.0);
            circle.setCenterY(40.0);
            circle.setRadius(40.0);
            imageView.setClip(circle);
            imageView.getStyleClass().add("hover-change");
                leftBox.getChildren().addAll(imageView);
                VBox rightBox = new VBox();
                rightBox.setSpacing(15);
                Label typeNoLabel = new Label(String.valueOf(archTypeEntity.getTypeNo()));
                typeNoLabel.getStyleClass().add("font-title");
                Label typeLabel = new Label(archTypeEntity.getType());
                Label createByLabel = new Label(archTypeEntity.getCreateBy());
                Label dateLabel = new Label(DateUtil.tranfer(archTypeEntity.getCreateTime()));
                Button lookBtn = new Button("查看");
                Button modifyBtn = new Button("修改");
                Button delBtn = new Button("删除");
                rightBox.getChildren().addAll(typeNoLabel, typeLabel,
                        createByLabel, dateLabel, lookBtn,modifyBtn,delBtn);
                //左右两个垂直布局加入水平布局
                hBox.getChildren().addAll(leftBox, rightBox);
                //水平布局加入大的内容容器
                add.getChildren().add(hBox);
                //删除按钮事件
                lookBtn.setOnAction(event -> {
                    showPara(archTypeEntity.getTypeNo());
                });
                // 修改按钮绑定事件
                modifyBtn.setOnAction(event -> {
                    update(archTypeEntity.getTypeNo(),archTypeEntity.getType());
                });
                delBtn.setOnAction(event -> {
                    boolean b = modifyTypeController.deleteOne(archTypeEntity.getTypeNo());
                    if (b){
                        add.getChildren().remove(hBox);
                    }

                });
            MainController.fileTypeFlag = false;
            }

    }

    private void showPara(Integer classId){
        ArchivesInfo archivesInfo= new ArchivesInfo();
        archivesInfo.setClassId(classId);
        fileTypeParaController.showPara(archivesInfo);
        fileTypeParaView.showView(Modality.NONE);
    }


    public void update(Integer typeNo,String type) {
        mainController.getJP_Show().getChildren().clear();
        mainController.getJP_Show().getChildren().add(updateTypeView.getView());
        updateTypeController.show(typeNo,type);
    }
}
