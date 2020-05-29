package com.xxq.filemanager.gui.controller;

import com.xxq.FileClient;
import com.xxq.filemanager.bean.SysUserInfo;
import com.xxq.filemanager.entity.ArchivesEntity;
import com.xxq.filemanager.entity.BorrowEntity;
import com.xxq.filemanager.service.interfaceI.ArchivesService;
import com.xxq.filemanager.service.interfaceI.BorrowService;
import com.xxq.filemanager.springJavafxSupport.FXMLController;
import com.xxq.filemanager.springJavafxSupport.FXMLView;
import com.xxq.filemanager.table.SimpleBorProperty;
import com.xxq.filemanager.table.SimpleFileProperty;
import com.xxq.filemanager.util.AlertUtil;
import com.xxq.filemanager.util.FileUtils;
import com.xxq.filemanager.util.MailUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.MessagingException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @ClassName MyBorrowController
 * @Description: TODO
 * @Author xxq
 * @Date 2020/3/24 20:21
 * @Version V1.0
 **/
@FXMLController
public class MyBorrowController implements Initializable {
    public  static ObservableList<SimpleBorProperty> list = FXCollections.observableArrayList();
    @FXML
    private TableView<SimpleBorProperty> dataTable;
    @FXML
    private TableColumn<SimpleBorProperty,String> numCol;
    @FXML
    private TableColumn<SimpleBorProperty,String> archNoCol;
    @FXML
    private TableColumn<SimpleBorProperty,String> backTimeCol;
    @FXML
    private TableColumn<SimpleBorProperty,String> archNameCol;
    @FXML
    private TableColumn<SimpleBorProperty,String> btnCol;
    @FXML
    private TableColumn checkCol;
    @Autowired
    BorrowService borrowService;
    @Autowired
    ArchivesService archivesService;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        numCol.setCellValueFactory(celldata ->celldata.getValue().getNumber());
        archNameCol.setCellValueFactory(celldata ->celldata.getValue().getArchName());
        archNoCol.setCellValueFactory(celldata ->celldata.getValue().getArchNo());
        backTimeCol.setCellValueFactory(celldata ->celldata.getValue().getBackTime());
        checkCol.setCellValueFactory(new PropertyValueFactory<SimpleBorProperty, CheckBox>("checkBox"));
        btnCol.setCellFactory((col)->{
                    TableCell<SimpleBorProperty, String> cell = new TableCell<SimpleBorProperty, String>(){

                        @Override
                        public void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);
                            //按钮显示文字
                            Button button = new Button("归还");
                            //按钮点击事件
                            button.setOnMouseClicked((col) -> {
                                //按钮事件自己添加
                                SimpleBorProperty sfp = new SimpleBorProperty();
                                int flag = 0;
                                int index = -1;
                                for(int i = 0;i<list.size();i++){
                                    sfp = list.get(i);
                                    if(sfp.getCheckBox().isSelected()){
                                        index = i;
                                        flag ++;
                                        String value = sfp.getArchNo().getValue();
                                        borrowService.backArch(value);
                                        ArchivesEntity archivesEntity = new ArchivesEntity();
                                        archivesEntity.setBorrowStatus("未借阅");
                                        archivesEntity.setArchNo(value);
                                        archivesService.updateBorStatus(archivesEntity);
                                        SysUserInfo sysUser = FileClient.sysUser;
                                        String email = sysUser.getEmail();
                                        try {
                                            MailUtil.backFileMail("728832542@qq.com",value);
                                        } catch (MessagingException e) {
                                            e.printStackTrace();
                                        }
                                        button.setText("已归还");
                                        AlertUtil.alert(Alert.AlertType.INFORMATION,"归还成功",FileClient.getStage());

                                    }
                                }
                                // 如果复选框中选中了不止一条数据
                                if(flag != 1){
                                    AlertUtil.alert(Alert.AlertType.WARNING,"请选择其中一条数据",FileClient.getStage());
                                    return;
                                }

                            });

                            if (empty) {
                                //如果此列为空默认不添加元素
                                setText(null);
                                setGraphic(null);
                            } else {
                                //加载按钮
                                this.setGraphic(button);
                            }
                        }
                    };
                    return cell;
                }
        );
    }
    public void showMyBorrow(){
        dataTable.getItems().clear();
        // 将数据绑定到tableView中
        List<BorrowEntity> borrowEntities = borrowService.queryByUserId(FileClient.sysUser.getId());
        for(int i = 0;i<borrowEntities.size();i++){
            list.add(new SimpleBorProperty(borrowEntities.get(i),i+1,borrowEntities.get(i).getCheckFlag()));
        }
        dataTable.setItems(this.list);
    }


}
