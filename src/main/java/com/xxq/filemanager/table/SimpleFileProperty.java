package com.xxq.filemanager.table;

import com.xxq.filemanager.bean.ArchivesInfo;
import com.xxq.filemanager.bean.SysUserInfo;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName SimpleFileProperty
 * @Description: TODO
 * @Author xxq
 * @Date 2020/2/25 13:26
 * @Version V1.0
 **/
public class SimpleFileProperty {
    private SimpleStringProperty id;
    private SimpleStringProperty archNo;
    private SimpleStringProperty number;
    private SimpleStringProperty archivesType;
    private SimpleStringProperty archivesName;
    private SimpleStringProperty borrowStatus;
    /**
     * 创建时间
     */
    private SimpleStringProperty createTime;
    /**
     * 创建人
     */
    private SimpleStringProperty createBy;

    private CheckBox checkBox= new CheckBox();
    public SimpleFileProperty() {
        id = new SimpleStringProperty();
        archivesType = new SimpleStringProperty();
        archivesName = new SimpleStringProperty();
        createBy = new SimpleStringProperty();
        borrowStatus = new SimpleStringProperty();
        createTime = new SimpleStringProperty();
    }
    public SimpleFileProperty(ArchivesInfo archives, int num, String flag){
        id = new SimpleStringProperty(String.valueOf(archives.getId()));
        archNo = new SimpleStringProperty(String.valueOf(archives.getArchNo()));
        number = new SimpleStringProperty(String.valueOf(num));
        archivesName = new SimpleStringProperty(archives.getArchivesName());
        archivesType = new SimpleStringProperty(archives.getArchivesType());
        borrowStatus = new SimpleStringProperty(archives.getBorrowStatus());
        createBy= new SimpleStringProperty(archives.getCreateBy());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        createTime = new SimpleStringProperty(dateFormat.format(archives.getCreateTime()));
        if("N".equals(flag)){
            checkBox.setSelected(false);
        }else if("Y".equals(flag)){
            checkBox.setSelected(true);
        }
    }

    public void setId(String id) {
        this.id = new SimpleStringProperty(id);
    }

    public void setArchivesName(String archivesName) {
        this.archivesName= new SimpleStringProperty(archivesName);
    }

    public void setArchivesType(String archivesType) {
        this.archivesType= new SimpleStringProperty(archivesType);
    }

    public void setCreateBy(String createBy) {
        this.createBy= new SimpleStringProperty(createBy);
    }


    public SimpleStringProperty getId() {
        return id;
    }
    public SimpleStringProperty getArchNo() {
        return archNo;
    }
    public SimpleStringProperty getNumber() {
        return number;
    }
    public SimpleStringProperty getBorrowStatus(){return borrowStatus;}
    public SimpleStringProperty  getArchivesType(){
        return archivesType;
    }

    public SimpleStringProperty getArchivesName() {
        return archivesName;
    }

    public SimpleStringProperty getCreateTime() {
        return createTime;
    }

    public SimpleStringProperty getCreateBy() {
        return createBy;
    }


    public CheckBox getCheckBox() {
        return checkBox;
    }

    public void setDelCheckBox(CheckBox checkBox) {
        this.checkBox = checkBox;
    }
}
