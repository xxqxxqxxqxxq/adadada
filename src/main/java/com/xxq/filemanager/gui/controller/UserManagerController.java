package com.xxq.filemanager.gui.controller;

import com.xxq.FileClient;
import com.xxq.filemanager.bean.DepartInfo;
import com.xxq.filemanager.bean.SysUserInfo;
import com.xxq.filemanager.gui.view.UserParaView;
import com.xxq.filemanager.springJavafxSupport.FXMLController;
import com.xxq.filemanager.service.interfaceI.UserService;
import com.xxq.filemanager.table.SimpleUserProperty;
import com.xxq.filemanager.util.AlertUtil;
import com.xxq.filemanager.util.Constants;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * @ClassName UserManagerController
 * @Description: 用户管理
 * @Author xxq
 * @Date 2020/2/10 17:59
 * @Version V1.0
 **/
@FXMLController
public class UserManagerController implements Initializable {
    private static Logger logger = Logger.getLogger(UserManagerController.class);
    public static ObservableList<SimpleUserProperty> list = FXCollections.observableArrayList();
    List<SysUserInfo> sysUserInfos=new ArrayList<>();
    @Autowired
    UserService userService;
    @Autowired
    UserParaController userParaController;
    @FXML
    private TextField TF_Username;
    @FXML
    private TextField TF_UserId;
    @FXML
    private ComboBox CMB_DepartName;

    @FXML
    private TableView<SimpleUserProperty> dataTable;

    @FXML
    private TableColumn<SimpleUserProperty,String> idCol;
    @FXML
    private TableColumn<SimpleUserProperty,String> usernameCol;
    @FXML
    private TableColumn<SimpleUserProperty,String> phoneCol;
    @FXML
    private TableColumn<SimpleUserProperty,String> roleCol;
    @FXML
    private TableColumn<SimpleUserProperty,String> departCol;
    @FXML
    private TableColumn delCol;

    public ComboBox getCMB_DepartName() {
        return CMB_DepartName;
    }

    public void setCMB_DepartName(ComboBox CMB_DepartName) {
        this.CMB_DepartName = CMB_DepartName;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /**
         * 配置表格，绑定表格的每列
         */
        idCol.setCellValueFactory(celldata ->celldata.getValue().getId());
        usernameCol.setCellValueFactory(cellData -> cellData.getValue().getUsername());
        phoneCol.setCellValueFactory(cellData -> cellData.getValue().getPhone());
        roleCol.setCellValueFactory(cellData -> cellData.getValue().getRole());
        departCol.setCellValueFactory(cellData -> cellData.getValue().getDepart());
        delCol.setCellValueFactory(new PropertyValueFactory<SimpleUserProperty, CheckBox>("delCheckBox"));

        CMB_DepartName.getItems().add(Constants.PLEASE_SELECT);
        for(DepartInfo d:FileClient.departInfoList){
            CMB_DepartName.getItems().add(d.getDepartName());
        }
      //  dataTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        userList();
    }

    /**
     * 点击查询按钮
     */
    @FXML
    public void btnQueryUser() {
        userList();
    }

    /**
     * 点击删除按钮
     */
    @FXML
    public void deleteUser() {
        logger.info("点击删除按钮");
       List<SysUserInfo> deleteList = new ArrayList<>();
        for(int i = 0;i<list.size();i++){
            SimpleUserProperty sup = list.get(i);
            // 判断复选框是否被选中,将选中的数据存到要删除的集合中
            if(sup.getDelCheckBox().isSelected()){
                SysUserInfo sysUserInfo = new SysUserInfo();
                sysUserInfo.setId(Integer.valueOf(sup.getId().getValue()));
                sysUserInfo.setDepartName(sup.getUsername().getValue());
                sysUserInfo.setUsername(sup.getUsername().getName());

                deleteList.add(sysUserInfo);
            }
        }
        // 如果未选数据
        if(deleteList.size() == 0){
            AlertUtil.alert(Alert.AlertType.WARNING,"没有选中要删除的数据",FileClient.getStage());
            return;
        }
        Optional<ButtonType> result = AlertUtil.alert(Alert.AlertType.CONFIRMATION,"确定删除用户？");
        //确定珊瑚虫
        if(result.get()==ButtonType.OK){
            userService.deleteOne(deleteList);
            // 删除成功界面重新加载数据
            userList();
            logger.info("删除成功"+deleteList);
            return;

        }
        logger.info("取消删除");
    }

    /**
     * 查看按钮
     */
    @FXML
    public void showUser() {
        logger.info("点击查看按钮");
        int flag = 0;
        int index = -1;
        for(int i = 0;i<list.size();i++){
            SimpleUserProperty sup = list.get(i);
            if(sup.getDelCheckBox().isSelected()){
                index = i;
                flag ++;
            }
        }
        // 如果复选框中选中了不止一条数据
        if(flag != 1){
            AlertUtil.alert(Alert.AlertType.WARNING,"请选择其中一条数据",FileClient.getStage());
            return;
        }

        //选中了其中一条数据
        SysUserInfo sysUserInfo = sysUserInfos.get(index);
        userParaController.showUser(sysUserInfo);
        FileClient.showView(UserParaView.class, Modality.NONE);
    }

    public void userList(){
        dataTable.getItems().clear();
        // 部门名称
        String departName = null;
        if(CMB_DepartName.getSelectionModel().getSelectedItem()!= null){
            departName = CMB_DepartName.getSelectionModel().getSelectedItem().toString();
            if (Constants.PLEASE_SELECT.equals(departName)){
                departName = null;
            }
        }

        // 用户编号
        Integer id = null;
        if(StringUtils.isNotEmpty(TF_UserId.getText().trim())){
            id = Integer.valueOf(TF_UserId.getText().trim());
        }

        // 用户姓名
        String username = null;
        if(StringUtils.isNotEmpty(TF_Username.getText().trim())){
            username = "%" + TF_Username.getText().trim() + "%";
        }

            // 查询数据
            sysUserInfos = userService.queryUserByPara(departName, id, username);

        // 将数据绑定到tableView中
        for(int i = 0;i<sysUserInfos.size();i++){
            list.add(new SimpleUserProperty(sysUserInfos.get(i),i+1,sysUserInfos.get(i).getCheckFlag()));
        }
        dataTable.setItems(this.list);
    }
}
