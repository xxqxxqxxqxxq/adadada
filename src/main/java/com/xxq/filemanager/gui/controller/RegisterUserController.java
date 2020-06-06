package com.xxq.filemanager.gui.controller;

import com.xxq.FileClient;
import com.xxq.filemanager.bean.DepartInfo;
import com.xxq.filemanager.bean.SysUserInfo;
import com.xxq.filemanager.entity.DepartEntity;
import com.xxq.filemanager.gui.view.RegisterUserView;
import com.xxq.filemanager.service.interfaceI.DepartService;
import com.xxq.filemanager.springJavafxSupport.FXMLController;
import com.xxq.filemanager.service.interfaceI.UserService;
import com.xxq.filemanager.util.AlertUtil;
import com.xxq.filemanager.util.Constants;
import com.xxq.filemanager.util.OpenPDF;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @ClassName RegisterUserController
 * @Description: TODO
 * @Author xxq
 * @Date 2020/2/27 12:48
 * @Version V1.0
 **/
@FXMLController
public class RegisterUserController implements Initializable {
    private Logger logger=Logger.getLogger(RegisterUserController.class);
    @Autowired
    UserParaController userParaController;
    @Autowired
    UserService userService;
    @FXML
    private TextField R_Username;
    @FXML
    private TextField R_Password;
    @FXML
    private TextField R_Phone;
    @FXML
    private TextField R_Email;
    @FXML
    private ComboBox R_CDepart;
    @Autowired
    RegisterUserView registerUserView;
    @Autowired
    DepartService departService;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        R_CDepart.getItems().add(Constants.PLEASE_SELECT);
        List<DepartInfo> departInfos = departService.selectAllDepart();
        for(DepartInfo d: departInfos){
            R_CDepart.getItems().add(d.getDepartName());
        }
    }

    /**
     * 注册一个用户
     */
    @FXML
    public void insert() {
        SysUserInfo sysUserInfo = new SysUserInfo();

        String username = R_Username.getText();
        String password = R_Password.getText();
        String phone = R_Phone.getText();
        String email = R_Email.getText();
        String departName = null;

        Integer count;

        if(R_CDepart.getSelectionModel().getSelectedItem()!= null){
            departName = R_CDepart.getSelectionModel().getSelectedItem().toString();
            // 设置机构名称
            count = departService.queryAmountByDepartName(departName);
            sysUserInfo.setDepartName(departName);
            userParaController.setdepart(sysUserInfo,departName);
            count = count +1;
            DepartEntity departEntity = new DepartEntity();
            departEntity.setAmount(count);
            departEntity.setDepartName(departName);
            departService.updateAmount(departEntity);
            if (Constants.PLEASE_SELECT.equals(departName)){
                departName = null;
            }
        }
        if (departName == null||username==null||password==null||email==null||phone==null) {
            AlertUtil.alert(Alert.AlertType.WARNING,"请完善必填信息！",FileClient.getStage());
        }else {
            sysUserInfo.setUsername(username);
            sysUserInfo.setPassword(password);
            sysUserInfo.setPhone(phone);
            sysUserInfo.setEmail(email);
            sysUserInfo.setPhoto(photoPath);
        }

        userService.insertUser(sysUserInfo);
        AlertUtil.alert(Alert.AlertType.INFORMATION,"注册成功",FileClient.getStage());
        registerUserView.hide();
        logger.info("注册成功！"+sysUserInfo);
    }
    public static String photoPath;
    @FXML
    public void choosePic() {
        OpenPDF openPDF = new OpenPDF();
        photoPath= openPDF.open();
    }
}
