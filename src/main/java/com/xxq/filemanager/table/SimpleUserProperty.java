package com.xxq.filemanager.table;

import com.xxq.filemanager.bean.SysUserInfo;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;

/**
 * @ClassName SimpleUserProperty
 * @Description: 绑定用户
 * @Author xxq
 * @Date 2020/2/25 13:26
 * @Version V1.0
 **/
public class SimpleUserProperty {
    private SimpleStringProperty id;
    private SimpleStringProperty username;
    private SimpleStringProperty phone;
    private SimpleStringProperty role;
    private SimpleStringProperty depart;
    private CheckBox delCheckBox= new CheckBox();


    public SimpleUserProperty() {
        id=new SimpleStringProperty();
        username=new SimpleStringProperty();
        phone=new SimpleStringProperty();
        role=new SimpleStringProperty();
        depart=new SimpleStringProperty();
    }
    public SimpleUserProperty(SysUserInfo user,int num,String flag){
        id = new SimpleStringProperty(String.valueOf(user.getId()));
        username = new SimpleStringProperty(user.getUsername());
        phone = new SimpleStringProperty(user.getPhone());
        role = new SimpleStringProperty(user.getRole());
        depart = new SimpleStringProperty(user.getDepartName());
        if("N".equals(flag)){
            delCheckBox.setSelected(false);
        }else if("Y".equals(flag)){
            delCheckBox.setSelected(true);
        }
    }

    public void setId(String id) {
        this.id = new SimpleStringProperty(id);
    }

    public void setUsername(String username) {
        this.username= new SimpleStringProperty(username);
    }

    public void setPhone(String phone) {
        this.phone= new SimpleStringProperty(phone);
    }

    public void setRole(String role) {
        this.role= new SimpleStringProperty(role);
    }

    public void setDepart(String depart) {
        this.depart= new SimpleStringProperty(depart);
    }

    public SimpleStringProperty getId() {
        return id;
    }

    public SimpleStringProperty  getUsername(){
        return username;
    }

    public SimpleStringProperty getPhone() {
        return phone;
    }

    public SimpleStringProperty getRole() {
        return role;
    }

    public SimpleStringProperty getDepart() {
        return depart;
    }


    public CheckBox getDelCheckBox() {
        return delCheckBox;
    }

    public void setDelCheckBox(CheckBox delCheckBox) {
        this.delCheckBox = delCheckBox;
    }
}
