package com.xxq.filemanager.gui.controller;

import com.xxq.FileClient;
import com.xxq.filemanager.bean.ArchivesInfo;
import com.xxq.filemanager.entity.ArchivesEntity;
import com.xxq.filemanager.entity.BorrowEntity;
import com.xxq.filemanager.service.interfaceI.ArchivesService;
import com.xxq.filemanager.springJavafxSupport.FXMLController;
import com.xxq.filemanager.table.SimpleBorProperty;
import com.xxq.filemanager.table.SimpleFileProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @ClassName FileTypeParaController
 * @Description: TODO
 * @Author xxq
 * @Date 2020/3/29 13:35
 * @Version V1.0
 **/
@FXMLController
public class FileTypeParaController implements Initializable {
    public  static ObservableList<SimpleFileProperty> list = FXCollections.observableArrayList();
    @FXML
    private TextField TF_Type;
    @FXML
    private TableView<SimpleFileProperty> dataTable;
    @FXML
    private TableColumn<SimpleFileProperty,String> numCol;
    @FXML
    private TableColumn<SimpleFileProperty,String> archNoCol;
    @FXML
    private TableColumn<SimpleFileProperty,String> archNameCol;
    @Autowired
    ArchivesService archivesService;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        numCol.setCellValueFactory(cellData -> cellData.getValue().getNumber());
        archNameCol.setCellValueFactory(cellData -> cellData.getValue().getArchivesName());
        archNoCol.setCellValueFactory(cellData -> cellData.getValue().getArchNo());
    }
    public void showPara(ArchivesInfo archivesInfo){
        TF_Type.setText(String.valueOf(archivesInfo.getClassId()));
            dataTable.getItems().clear();
            // 将数据绑定到tableView中
        List<ArchivesInfo> archivesInfoList = archivesService.queryArchByClassId(archivesInfo);
        for(int i = 0;i<archivesInfoList.size();i++){
                list.add(new SimpleFileProperty(archivesInfoList.get(i),i+1,archivesInfoList.get(i).getCheckFlag()));
            }
            dataTable.setItems(this.list);

    }

}
