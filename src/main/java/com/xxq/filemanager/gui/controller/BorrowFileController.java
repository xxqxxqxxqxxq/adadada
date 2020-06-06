package com.xxq.filemanager.gui.controller;

import com.xxq.filemanager.bean.ArchivesInfo;
import com.xxq.filemanager.entity.BorrowEntity;
import com.xxq.filemanager.gui.view.BorApproveView;
import com.xxq.filemanager.service.interfaceI.BorrowService;
import com.xxq.filemanager.springJavafxSupport.FXMLController;
import com.xxq.filemanager.table.SimpleBorProperty;
import com.xxq.filemanager.table.SimpleFileProperty;
import com.xxq.filemanager.util.MailUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.MessagingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @ClassName BorrowFileController
 * @Description: TODO
 * @Author xxq
 * @Date 2020/3/17 22:09
 * @Version V1.0
 **/
@FXMLController
public class BorrowFileController implements Initializable {
    public  static ObservableList<SimpleBorProperty> list = FXCollections.observableArrayList();
    @FXML
    private TableView<SimpleBorProperty> dataTable;

    @FXML
    private TableColumn<SimpleBorProperty,String> numberCol;
    @FXML
    private TableColumn<SimpleBorProperty,String> archCol;
    @FXML
    private TableColumn<SimpleBorProperty,String> dateCol;
    @FXML
    private TableColumn<SimpleBorProperty,String> nameCol;
    @FXML
    private TableColumn<SimpleBorProperty,String> userIdCol;
    @FXML
    private TableColumn<SimpleBorProperty,String> backCol;
    @FXML
    private TableColumn<SimpleBorProperty,String> approveCol;
    @FXML
    private TableColumn checkCol;
    @FXML
    private TableColumn buttonCol;
    @FXML
    private ImageView img;
    public TableColumn getCheckCol() {
        return checkCol;
    }

    public void setCheckCol(TableColumn checkCol) {
        this.checkCol = checkCol;
    }

    public TableColumn getButtonCol() {
        return buttonCol;
    }

    public void setButtonCol(TableColumn buttonCol) {
        this.buttonCol = buttonCol;
    }

    public TableColumn getLabelCol() {
        return labelCol;
    }

    public void setLabelCol(TableColumn labelCol) {
        this.labelCol = labelCol;
    }
    @FXML
    private Label count;

    public Label getCount() {
        return count;
    }

    public void setCount(Label count) {
        this.count = count;
    }

    @FXML
    private TableColumn <SimpleBorProperty,String>labelCol;
    @Autowired
    BorrowService borrowService;
    @Autowired
    BorApproveView borApproveView;
    @Autowired
    BorApproveController borApproveController;
    @Autowired
    MainController mainController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        img.setImage(new Image("/photos/breq.png"));
        count.setText(String.valueOf(borrowService.toApprove()));
        numberCol.setCellValueFactory(celldata ->celldata.getValue().getNumber());
        archCol.setCellValueFactory(celldata ->celldata.getValue().getArchNo());
        dateCol.setCellValueFactory(celldata ->celldata.getValue().getBDate());
        userIdCol.setCellValueFactory(celldate->celldate.getValue().getUserId());
        backCol.setCellValueFactory(celldata ->celldata.getValue().getBackTime());
        nameCol.setCellValueFactory(celldata ->celldata.getValue().getBName());
        approveCol.setCellValueFactory(celldata ->celldata.getValue().getApprove());
        checkCol.setCellValueFactory(new PropertyValueFactory<SimpleBorProperty, CheckBox>("checkBox"));
        buttonCol.setCellFactory((col)->{
                    TableCell<SimpleBorProperty, String> cell = new TableCell<SimpleBorProperty, String>(){

                        @Override
                        public void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);
                            //按钮显示文字
                            Button button = new Button("提醒");


                            //按钮点击事件
                            button.setOnMouseClicked((col) -> {
                                //按钮事件自己添加
                               button.setText("已提醒");
                                try {
                                    MailUtil.sendAlarmMail();
                                } catch (MessagingException e) {
                                    e.printStackTrace();
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
            showList();
    }
    public void showList(){
        dataTable.getItems().clear();
        List<BorrowEntity> borrowEntities = new ArrayList<>();
        // 将数据绑定到tableView中
        borrowEntities = borrowService.queryAll();
        for(int i = 0;i<borrowEntities.size();i++){
            list.add(new SimpleBorProperty(borrowEntities.get(i),i+1,borrowEntities.get(i).getCheckFlag()));
        }
        dataTable.setItems(this.list);
    }
    @FXML
    public void toApprove() {
        mainController.getJP_Show().getChildren().clear();
        mainController.getJP_Show().getChildren().add(borApproveView.getView());
        borApproveController.showAllToApprove();
    }
}
