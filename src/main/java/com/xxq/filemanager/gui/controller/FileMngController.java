package com.xxq.filemanager.gui.controller;

import com.xxq.FileClient;
import com.xxq.filemanager.bean.ArchivesInfo;
import com.xxq.filemanager.gui.view.FileAddView;
import com.xxq.filemanager.gui.view.FileParaView;
import com.xxq.filemanager.service.interfaceI.ArchivesService;
import com.xxq.filemanager.springJavafxSupport.FXMLController;
import com.xxq.filemanager.table.SimpleFileProperty;
import com.xxq.filemanager.table.SimpleUserProperty;
import com.xxq.filemanager.util.AlertUtil;
import com.xxq.filemanager.util.Constants;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import java.util.ResourceBundle;

/**
 * @ClassName FileMngController
 * @Description: 档案管理
 * @Author xxq
 * @Date 2020/2/14 10:47
 * @Version V1.0
 **/
@FXMLController
public class FileMngController implements Initializable {
    private Logger logger=Logger.getLogger(FileParaController.class);
    public  static ObservableList<SimpleFileProperty> list = FXCollections.observableArrayList();
    private List<ArchivesInfo> archivesInfoList = new ArrayList<>();
    @FXML
    private TableView<SimpleFileProperty> dataTable;
    @FXML
    private TextField TF_ID;
    @FXML
    private TextField TF_ArchName;
    @FXML
    private TableColumn<SimpleFileProperty,String> idCol;
    @FXML
    private TableColumn<SimpleFileProperty,String> numCol;
    @FXML
    private TableColumn<SimpleFileProperty,String> createCol;
    @FXML
    private TableColumn<SimpleFileProperty,String> timeCol;
    @FXML
    private TableColumn<SimpleFileProperty,String> statusCol;

    @FXML
    private TableColumn cheCol;
    @Autowired
    ArchivesService archivesService;
    @Autowired
    FileParaController fileParaController;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /**
         * 配置表格，绑定表格的每列
         */
        idCol.setCellValueFactory(celldata ->celldata.getValue().getId());
        numCol.setCellValueFactory(cellData -> cellData.getValue().getNumber());
        timeCol.setCellValueFactory(cellData->cellData.getValue().getCreateTime());
        createCol.setCellValueFactory(cellData -> cellData.getValue().getCreateBy());
        statusCol.setCellValueFactory(cellData -> cellData.getValue().getBorrowStatus());
        cheCol.setCellValueFactory(new PropertyValueFactory<SimpleFileProperty, CheckBox>("checkBox"));
        fileList();
    }

    public void addFile(){
        System.out.println("点击添加按钮");

        FileClient.showView(FileAddView.class, Modality.NONE);
    }
    @FXML
    public void queryByPara() {
        logger.info("点击查询按钮");
        fileList();
    }
    @FXML
    public void look() {
        logger.info("点击查看按钮");
        int flag = 0;
        int index = -1;
        for(int i = 0;i<list.size();i++){
            SimpleFileProperty sfp = list.get(i);
            if(sfp.getCheckBox().isSelected()){
                index = i;
                flag ++;
            }
        }
        // 如果复选框中选中了不止一条数据
        if(flag != 1){
            AlertUtil.alert(Alert.AlertType.WARNING,"请选择其中一条数据",FileClient.getStage());
            return;
        }
        ArchivesInfo archivesInfo = archivesInfoList.get(index);
        Integer id = archivesInfo.getId();
        fileParaController.showArchPara(id);
        FileClient.showView(FileParaView.class,Modality.NONE);
    }

    private void fileList(){
        dataTable.getItems().clear();


        // 档案编号
        Integer id = null;
        if(StringUtils.isNotEmpty(TF_ID.getText().trim())){
            id = Integer.valueOf(TF_ID.getText().trim());
        }

        // 档案姓名
        String archivesName = null;
        if(StringUtils.isNotEmpty(TF_ArchName.getText().trim())){
            archivesName = "%" + TF_ArchName.getText().trim() + "%";
        }
        ArchivesInfo archivesInfo = new ArchivesInfo();
        archivesInfo.setId(id);
        archivesInfo.setArchivesName(archivesName);

        if(id == null && archivesName == null){
            // 查询数据
             archivesInfoList = archivesService.queryAllArch();
        }else {
            archivesInfoList = archivesService.queryArchByPara(archivesInfo);
        }

        // 将数据绑定到tableView中
        for(int i = 0;i<archivesInfoList.size();i++){
            list.add(new SimpleFileProperty(archivesInfoList.get(i),i+1,archivesInfoList.get(i).getCheckFlag()));
        }
        dataTable.setItems(this.list);
    }

}
