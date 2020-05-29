package com.xxq.filemanager.gui.controller;

import com.xxq.FileClient;
import com.xxq.filemanager.bean.ArchivesInfo;
import com.xxq.filemanager.bean.SysUserInfo;
import com.xxq.filemanager.gui.view.FileAddView;
import com.xxq.filemanager.gui.view.FileParaView;
import com.xxq.filemanager.gui.view.UploadView;
import com.xxq.filemanager.service.interfaceI.ArchivesService;
import com.xxq.filemanager.springJavafxSupport.FXMLController;
import com.xxq.filemanager.table.SimpleBorProperty;
import com.xxq.filemanager.table.SimpleFileProperty;
import com.xxq.filemanager.table.SimpleUserProperty;
import com.xxq.filemanager.util.*;
import javafx.beans.property.SimpleStringProperty;
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

import javax.mail.MessagingException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    private TableColumn btnCol;
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
        idCol.setCellValueFactory(celldata ->celldata.getValue().getArchNo());
        numCol.setCellValueFactory(cellData -> cellData.getValue().getNumber());
        timeCol.setCellValueFactory(cellData->cellData.getValue().getCreateTime());
        createCol.setCellValueFactory(cellData -> cellData.getValue().getCreateBy());
        statusCol.setCellValueFactory(cellData -> cellData.getValue().getBorrowStatus());
        cheCol.setCellValueFactory(new PropertyValueFactory<SimpleFileProperty, CheckBox>("checkBox"));
        btnCol.setCellFactory((col)->{
                    TableCell<SimpleFileProperty, String> cell = new TableCell<SimpleFileProperty, String>(){

                        @Override
                        public void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);
                            //按钮显示文字
                            Button button = new Button("浏览");


                            //按钮点击事件
                            button.setOnMouseClicked((col) -> {
                                SimpleFileProperty sfp = new SimpleFileProperty();
                                int flag = 0;
                                int index = -1;
                                for(int i = 0;i<list.size();i++){
                                     sfp = list.get(i);
                                    if(sfp.getCheckBox().isSelected()){
                                        index = i;
                                        flag ++;
                                        String value = sfp.getArchNo().getValue();
                                        String path = archivesService.findPath(value);
                                        if(path!=null){
                                            value = null;
                                        }
                                        System.out.println(value);

                                        FileUtils fileUtils = new FileUtils();
                                        try {
                                            fileUtils.loadPdf(value,path);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
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
        String archNo = archivesInfo.getArchNo();
        String path = archivesService.findPath(archNo);
        if(path!=null){
            AlertUtil.alert(Alert.AlertType.WARNING,"该档案没有参数，请直接阅读文件", FileClient.getStage());
            return;
        }
        fileParaController.showArchPara(archNo);
        FileClient.showView(FileParaView.class,Modality.NONE);
    }

    public void fileList(){
        dataTable.getItems().clear();


        // 档案编号
        String archNo = null;
        if(StringUtils.isNotEmpty(TF_ID.getText().trim())){
            archNo = TF_ID.getText().trim();
        }

        // 档案姓名
        String archivesName = null;
        if(StringUtils.isNotEmpty(TF_ArchName.getText().trim())){
            archivesName = "%" + TF_ArchName.getText().trim() + "%";
        }
        ArchivesInfo archivesInfo = new ArchivesInfo();
        archivesInfo.setArchNo(archNo);
        archivesInfo.setArchivesName(archivesName);

        if(archNo == null && archivesName == null){
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
    @FXML
    public void deleteFile() {
        logger.info("点击删除按钮");
        List<ArchivesInfo> deleteList = new ArrayList<>();
        for(int i = 0;i<list.size();i++){
            SimpleFileProperty sfp = list.get(i);
            // 判断复选框是否被选中,将选中的数据存到要删除的集合中
            if(sfp.getCheckBox().isSelected()){
                ArchivesInfo archivesInfo = new ArchivesInfo();
                archivesInfo.setId(Integer.valueOf(sfp.getId().getValue()));
                archivesInfo.setArchNo(sfp.getArchNo().getValue());
                archivesInfo.setArchivesName(sfp.getArchivesName().getValue());
                archivesInfo.setBorrowStatus(sfp.getBorrowStatus().getValue());

                deleteList.add(archivesInfo);
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
            archivesService.deleteOneFile(deleteList);
            // 删除成功界面重新加载数据
            fileList();
            logger.info("删除成功"+deleteList);
            return;

        }
        logger.info("取消删除");
    }
    @FXML
    public void upload() {
        FileClient.showView(UploadView.class, Modality.NONE);
    }
//    @FXML
//    public void showDetails() {
//        OpenPDF openPDF = new OpenPDF();
//        openPDF.open();
//    }
}
