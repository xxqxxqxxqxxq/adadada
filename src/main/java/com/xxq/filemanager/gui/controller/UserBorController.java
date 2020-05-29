package com.xxq.filemanager.gui.controller;

import com.xxq.FileClient;
import com.xxq.filemanager.bean.ArchivesInfo;
import com.xxq.filemanager.bean.SysUserInfo;
import com.xxq.filemanager.entity.ArchivesEntity;
import com.xxq.filemanager.entity.BorrowEntity;
import com.xxq.filemanager.service.interfaceI.ArchivesService;
import com.xxq.filemanager.service.interfaceI.BorrowService;
import com.xxq.filemanager.springJavafxSupport.FXMLController;
import com.xxq.filemanager.util.AlertUtil;
import com.xxq.filemanager.util.MailUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.MessagingException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @ClassName UserBorController
 * @Description: TODO
 * @Author xxq
 * @Date 2020/3/29 18:03
 * @Version V1.0
 **/
@FXMLController
public class UserBorController implements Initializable {
    @FXML
    private TextField name;
    @FXML
    private TextField archNo;
    @FXML
    private TextField userId;
    @FXML
    private DatePicker backTime;
    @Autowired
    BorrowService borrowService;
    @Autowired
    ArchivesService archivesService;
    @FXML
    private ImageView img;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        img.setImage(new Image("/photos/yjyh.jpeg"));
    }
    @FXML
    public void submit() {
        ArchivesInfo archivesInfo = new ArchivesInfo();
        archivesInfo.setArchNo(archNo.getText());
        List<ArchivesInfo> archivesInfos = archivesService.queryArchByPara(archivesInfo);
        ArchivesInfo archivesInfo1 = archivesInfos.get(0);

        if (archivesInfo1==null){
            AlertUtil.alert(Alert.AlertType.WARNING,"该档案不存在,无法借阅", FileClient.getStage());
            return;
        }
        if ("已借阅".equals(archivesInfo1.getBorrowStatus())){
            AlertUtil.alert(Alert.AlertType.WARNING,"该档案已被借阅,无法借阅", FileClient.getStage());
            return;
        }
        if(name.getText()==null||archNo.getText()==null||name.getText()==null||backTime==null){
            AlertUtil.alert(Alert.AlertType.WARNING,"请完善借阅信息", FileClient.getStage());

        }
        BorrowEntity borrowEntity = new BorrowEntity();
        borrowEntity.setArchivesId(archNo.getText());
        borrowEntity.setBName(name.getText());
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
        Date backDate = new Date();
        try {
            backDate = sdf.parse(backTime.getValue().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        borrowEntity.setBackTime(backDate);
        borrowEntity.setUserId(Integer.valueOf(userId.getText()));
        int i = borrowService.addOne(borrowEntity);
        System.out.println(i);
        SysUserInfo sysUser = FileClient.sysUser;
        String username = sysUser.getUsername();
        String email = sysUser.getEmail();
        try {
            MailUtil.sendMail(email,username);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        AlertUtil.alert(Alert.AlertType.INFORMATION,"借阅请求已经发送", FileClient.getStage());
    }
}
