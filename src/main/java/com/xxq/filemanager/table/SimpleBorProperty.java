package com.xxq.filemanager.table;

import com.xxq.filemanager.bean.ArchivesInfo;
import com.xxq.filemanager.entity.BorrowEntity;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

import java.text.SimpleDateFormat;

/**
 * @ClassName SimpleBorProperty
 * @Description: TODO
 * @Author xxq
 * @Date 2020/3/22 15:24
 * @Version V1.0
 **/
public class SimpleBorProperty {
    private SimpleStringProperty id;
    private SimpleStringProperty userId;
    private SimpleStringProperty archNo;
    private SimpleStringProperty archName;
    private SimpleStringProperty BDate;
    private SimpleStringProperty approve;
    private SimpleStringProperty BName;
    private SimpleStringProperty number;
    /**
     * 归还
     */
    private SimpleStringProperty backTime;

    private SimpleStringProperty aText;
    private CheckBox checkBox= new CheckBox();
    private Button button=new Button("提醒");
    public SimpleBorProperty() {
        id = new SimpleStringProperty();
        archNo= new SimpleStringProperty();
        userId = new SimpleStringProperty();
        BDate = new SimpleStringProperty();
        approve = new SimpleStringProperty();
        archName = new SimpleStringProperty();
        backTime = new SimpleStringProperty();
        BName = new SimpleStringProperty();
        number = new SimpleStringProperty();
        aText = new SimpleStringProperty();
    }
    public SimpleBorProperty(BorrowEntity borrowEntity, int num, String flag){
        id = new SimpleStringProperty(String.valueOf(borrowEntity.getId()));
        archNo = new SimpleStringProperty(String.valueOf(borrowEntity.getArchivesId()));
        userId = new SimpleStringProperty(String.valueOf(borrowEntity.getUserId()));
        number = new SimpleStringProperty(String.valueOf(num));
        archName =new SimpleStringProperty(borrowEntity.getArchName());
        approve = new SimpleStringProperty(borrowEntity.getApprove());
        BName = new SimpleStringProperty(borrowEntity.getBName());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        backTime = new SimpleStringProperty(dateFormat.format(borrowEntity.getBackTime()));
        BDate= new SimpleStringProperty(dateFormat.format(borrowEntity.getBDate()));
        if("N".equals(flag)){
            checkBox.setSelected(false);
        }else if("Y".equals(flag)){
            checkBox.setSelected(true);
        }
    }
    public void setArchName(String archName){this.archName = new SimpleStringProperty(archName);}
    public void setId(String id) {
        this.id = new SimpleStringProperty(id);
    }
    public void setUserId(String userId) {
        this.userId = new SimpleStringProperty(userId);
    }

    public void setArchNo(String archNo) {
        this.archNo= new SimpleStringProperty(archNo);
    }

    public void setApprove(String approve) {
        this.approve= new SimpleStringProperty(approve);
    }

    public void setBName(String BName) {
        this.BName= new SimpleStringProperty(BName);
    }

    public void setBackTime(String backTime) {
        this.backTime= new SimpleStringProperty(backTime);
    }
    public void setBDate(String BDate){this.BDate=new SimpleStringProperty(BDate);}

    public SimpleStringProperty getId() {
        return id;
    }
    public SimpleStringProperty getUserId() {
        return userId;
    }
    public SimpleStringProperty getArchNo() {
        return archNo;
    }
    public SimpleStringProperty getBDate() {
        return BDate;
    }
    public SimpleStringProperty getApprove() {
        return approve;
    }
    public SimpleStringProperty getBName() {
        return BName;
    }
    public SimpleStringProperty getArchName() {
        return archName;
    }
    public SimpleStringProperty getBackTime() {
        return backTime;
    }
    public CheckBox getCheckBox() {
        return checkBox;
    }

    public SimpleStringProperty getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number=new SimpleStringProperty(number);
    }
    public SimpleStringProperty getAText() {
        return aText;
    }

    public void setAText(String aText) {
        this.aText=new SimpleStringProperty(aText);
    }
    public Button getButton(){return button;}
    public void setCheckBox(CheckBox delCheckBox) {
        this.checkBox = delCheckBox;
    }
}
