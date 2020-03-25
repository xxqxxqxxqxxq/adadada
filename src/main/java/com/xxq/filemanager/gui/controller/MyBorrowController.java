package com.xxq.filemanager.gui.controller;

import com.xxq.FileClient;
import com.xxq.filemanager.entity.BorrowEntity;
import com.xxq.filemanager.service.interfaceI.BorrowService;
import com.xxq.filemanager.springJavafxSupport.FXMLController;
import com.xxq.filemanager.springJavafxSupport.FXMLView;
import com.xxq.filemanager.table.SimpleBorProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.springframework.beans.factory.annotation.Autowired;

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
    @Autowired
    BorrowService borrowService;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        numCol.setCellValueFactory(celldata ->celldata.getValue().getNumber());
        archNameCol.setCellValueFactory(celldata ->celldata.getValue().getArchName());
        archNoCol.setCellValueFactory(celldata ->celldata.getValue().getArchNo());
        backTimeCol.setCellValueFactory(celldata ->celldata.getValue().getBackTime());

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
