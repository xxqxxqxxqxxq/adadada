package com.xxq.filemanager.gui.controller;

import com.xxq.FileClient;
import com.xxq.filemanager.bean.DepartInfo;
import com.xxq.filemanager.bean.SysUserInfo;
import com.xxq.filemanager.entity.BorrowEntity;
import com.xxq.filemanager.gui.view.AddDepartView;
import com.xxq.filemanager.gui.view.DepartSearchView;
import com.xxq.filemanager.service.interfaceI.DepartService;
import com.xxq.filemanager.service.interfaceI.UserService;
import com.xxq.filemanager.springJavafxSupport.FXMLController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.cell.TextFieldTreeCell;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * @ClassName DepartSearchController
 * @Description: TODO
 * @Author xxq
 * @Date 2020/3/25 10:02
 * @Version V1.0
 **/
@FXMLController
public class DepartSearchController implements Initializable {
    @FXML
    private TextField TF_Search;
    @Autowired
    FileParaController fileParaController;
    @Autowired
    DepartService departService;
    @Autowired
    UserService userService;
    @FXML
    private TreeView<String> tv;
    @FXML
    private ScrollPane sc;

    @Autowired
    AddDepartView addDepartView;

    /**
     * 鼠标右击事件
     */
    private ContextMenu contextMenu = new ContextMenu();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        initTreeView();
    }
    TreeItem<String> root = new TreeItem<>("部门");
    private void initTreeView(){

        List<DepartInfo> departInfos = departService.selectAllDepart();
        FileClient.departInfoList.addAll(departInfos);
        tv.setRoot(root);
        for (DepartInfo d:departInfos){
            TreeItem<String> i1 = new TreeItem<>(d.getDepartName());
            root.setExpanded(false);
            root.getChildren().addAll(i1);
            List<SysUserInfo> sysUserInfos = userService.queryUserByDID(d.getId());
            for (SysUserInfo s:sysUserInfos){
                TreeItem<String> i4 = new TreeItem<>(s.getUsername());
                i1.getChildren().addAll(i4);
            }

        }

    }
    @FXML
    public void mouseClick(MouseEvent mouseEvent) {
        if(mouseEvent.getButton().equals(MouseButton.SECONDARY))
        {
            contextMenu.hide();
            TreeItem<String> item = tv.getSelectionModel().getSelectedItem();
            System.out.println("Selected Text : " + item.getValue());
            MenuItem menuItem = new MenuItem();
            MenuItem menuItem1 = new MenuItem();
            if(item.getValue().equals("部门")){
                // 鼠标右击后显示的菜单
                menuItem = new MenuItem("添加");
                // 点击菜单的响应事件
                menuItem.setOnAction(actionEvent -> {
                    map.put("item",item);
                    showAdd();
                });
                // 将菜单添加到鼠标右击显示
                contextMenu = new ContextMenu(menuItem);
            }else {
                menuItem1 = new MenuItem("删除");
                menuItem1.setOnAction(actionEvent -> {
                    String departName = item.getValue();
                    departService.deleteOne(departName);
                    SysUserInfo sysUserInfo = new SysUserInfo();
                    setdepart(sysUserInfo,departName);

                    departService.deleteUserByDId(sysUserInfo.getDepartId());
                    root.getChildren().remove(item);
                });
                contextMenu = new ContextMenu(menuItem1);
            }

            contextMenu.show(sc,mouseEvent.getSceneX(),mouseEvent.getScreenY());

        }else {
            contextMenu.hide();
        }
    }
   public static Map map = new HashMap();

  public void addOne(String departName,TreeItem<String> item){
      TreeItem<String> i1 = new TreeItem<>(departName);
      item.setExpanded(false);
      item.getChildren().add(i1);
      departService.addOneDepart(departName);
      map.remove("item");
  }

  public void showAdd(){
        addDepartView.showView(Modality.NONE);
  }

    public void setdepart(SysUserInfo sysUserInfo,String text){
        switch (text){
            case "党群部门" :
                sysUserInfo.setDepartId(1);
                break;
            case "行政部门":
                sysUserInfo.setDepartId(2);
                break;
            case "教学单位":
                sysUserInfo.setDepartId(3);
                break;
            case "科研单位":
                sysUserInfo.setDepartId(4);
                break;
            case "党基层组织":
                sysUserInfo.setDepartId(5);
                break;
        }
    }

}
