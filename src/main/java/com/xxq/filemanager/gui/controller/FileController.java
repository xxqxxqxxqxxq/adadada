package com.xxq.filemanager.gui.controller;

import com.xxq.FileClient;
import com.xxq.filemanager.bean.ArchivesInfo;
import com.xxq.filemanager.entity.BorrowEntity;
import com.xxq.filemanager.gui.view.FileParaView;
import com.xxq.filemanager.gui.view.FileView;
import com.xxq.filemanager.service.interfaceI.ArchivesService;
import com.xxq.filemanager.service.interfaceI.BorrowService;
import com.xxq.filemanager.springJavafxSupport.FXMLController;
import com.xxq.filemanager.util.AlertUtil;
import com.xxq.filemanager.util.FileUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @ClassName FileController
 * @Description: TODO
 * @Author xxq
 * @Date 2020/3/23 18:45
 * @Version V1.0
 **/
@FXMLController
@Scope("prototype")
public class FileController implements Initializable {
    private FileView fileView;
    @FXML
    private ImageView fileImg;
    @FXML
    private Pane P_FPane;
    @FXML
    private Label L_Info;
    @FXML
    private Label L_ArchNo;
    @Autowired
    ArchivesService archivesService;
    @Autowired
    FileParaController fileParaController;
    @Autowired
    BorrowService borrowService;
    public Label getL_ArchNo() {
        return L_ArchNo;
    }

    public void setL_ArchNo(Label l_ArchNo) {
        L_ArchNo = l_ArchNo;
    }

    public Label getL_Info() {
        return L_Info;
    }

    public void setL_Info(Label l_Info) {
        L_Info = l_Info;
    }

    public ImageView getFileImg() {
        return fileImg;
    }

    public void setFileImg(ImageView fileImg) {
        this.fileImg = fileImg;
    }

    public FileView getFileView() {
        return fileView;
    }

    public void setFileView(FileView fileView) {
        this.fileView = fileView;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    public void mouseClick() throws IOException {
        String  archNo = L_ArchNo.getText();
        BorrowEntity borrowEntity = borrowService.queryByArchNo(archNo);

            if(borrowEntity==null){
                AlertUtil.alert(Alert.AlertType.WARNING,"您没有权限查看档案");
                return;
            }
        String path = archivesService.findPath(archNo);

          if(FileClient.sysUser.getId() == borrowEntity.getUserId()){
              if(path!=null){
                  archNo = null;
                  FileUtils fileUtils = new FileUtils();
                  fileUtils.loadPdf(archNo,path);
                  return;
              }
                FileClient.showView(FileParaView.class, Modality.NONE);
                fileParaController.showArchPara(borrowEntity.getArchivesId());

            }else {
                AlertUtil.alert(Alert.AlertType.WARNING,"您没有权限查看档案");
            }


    }





}
