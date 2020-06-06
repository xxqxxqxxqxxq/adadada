package com.xxq.filemanager.gui.controller;

import com.xxq.FileClient;
import com.xxq.filemanager.bean.ArchivesInfo;
import com.xxq.filemanager.bean.SysUserInfo;
import com.xxq.filemanager.config.BorrowMap;
import com.xxq.filemanager.entity.ArchivesEntity;
import com.xxq.filemanager.entity.BorrowEntity;
import com.xxq.filemanager.gui.view.BorApproveView;
import com.xxq.filemanager.gui.view.BorrowFileView;
import com.xxq.filemanager.service.interfaceI.ArchivesService;
import com.xxq.filemanager.service.interfaceI.BorrowService;
import com.xxq.filemanager.service.interfaceI.UserService;
import com.xxq.filemanager.springJavafxSupport.FXMLController;
import com.xxq.filemanager.util.AlertUtil;
import com.xxq.filemanager.util.DateUtil;
import com.xxq.filemanager.util.MailUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.MessagingException;
import java.net.URL;
import java.util.*;

/**
 * @ClassName BorApproveController
 * @Description: 借阅审批
 * @Author xxq
 * @Date 2020/3/31 19:00
 * @Version V1.0
 **/
@FXMLController
public class BorApproveController implements Initializable {

    @Autowired
    BorrowService borrowService;
    @FXML
    private AnchorPane Show;
    @Autowired
    MainController mainController;
    @Autowired
    BorrowFileView borrowFileView;
    @Autowired
    BorrowFileController borrowFileController;
    @Autowired
    ArchivesService archivesService;
    @Autowired
    UserService userService;

    List<BorrowEntity>  borrowEntities = new ArrayList<>();
   @Autowired
    BorrowMap borrowMap;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void showAllToApprove(){
         borrowEntities = borrowService.queryAllToApprove();
       for (int i =0;i<borrowEntities.size();i++){
           BorrowEntity borrowEntity = borrowEntities.get(i);
           Label label = new Label(borrowEntity.getBName()+"的借阅申请：借阅档案号："+borrowEntity.getArchivesId()+"  归还日期："+ DateUtil.tranfer(borrowEntity.getBackTime()));
           label.setLayoutY(i*30);

               Show.getChildren().add(label);


       }


    }
    @FXML
    public void approve() {

        for(int i =0;i<borrowEntities.size();i++){

            BorrowEntity borrowEntity = borrowEntities.get(i);
            // 获取档案号
            String archivesId = borrowEntity.getArchivesId();
            // 获取借阅者id
            Integer userId = borrowEntity.getUserId();

            List<SysUserInfo> sysUserInfos = userService.queryUserByPara(null, userId, null);
            String email = sysUserInfos.get(0).getEmail();
            if(!borrowMap.getMap().containsKey(archivesId)){
                ArchivesInfo archivesInfo = new ArchivesInfo();
                archivesInfo.setArchNo(archivesId);
                ArchivesInfo archivesInfo1 = archivesService.queryArchByPara(archivesInfo).get(0);
                // 如果档案已经被借阅
                if(archivesInfo1.getBorrowStatus().equals("已借阅")){
                    AlertUtil.alert(Alert.AlertType.INFORMATION,"该档案已经被借阅,请通知借阅人归还后继续审批");
                    // 发送邮件给借阅者
                    try {
                        MailUtil.backMail(email,borrowEntity);
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                    return;
                }

                borrowEntity.setLendFlag(1);
                borrowEntity.setApprove(FileClient.sysUser.getUsername());
                // 借阅审批，修改借阅表的借阅状态
                borrowService.approve(borrowEntity);
                ArchivesEntity archivesEntity = new ArchivesEntity();
                archivesEntity.setBorrowStatus("已借阅");
                archivesEntity.setArchNo(borrowEntity.getArchivesId());
                // 修改档案表的借阅状态
                archivesService.updateBorStatus(archivesEntity);

                // 发送邮件给借阅者
                try {
                    MailUtil.backMail(email,borrowEntity);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
                System.out.println("111");
//                borrowEntities.remove(borrowEntity);
                borrowMap.getMap().put(archivesId,1);
            }else {
                // 发送邮件给借阅者
                try {
                    MailUtil.backMail(email,borrowEntity);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
                System.out.println("档案已被借阅");
            }


        }
        borrowFileController.getCount().setText("0");
        AlertUtil.alert(Alert.AlertType.INFORMATION,"审批成功", FileClient.getStage());
        borrowFileController.showList();
        Show.getChildren().clear();
    }
    @FXML
    public void backToBor() {
        mainController.getJP_Show().getChildren().clear();
        mainController.getJP_Show().getChildren().add(borrowFileView.getView());
    }
}
