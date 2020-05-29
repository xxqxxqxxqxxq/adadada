package com.xxq.filemanager.entity;

import javafx.fxml.Initializable;

/**
 * @ClassName :ArchPathEntity
 * @Description:
 * @Author xxq
 * @Date 2020/4/23  19:11
 * @Version V1.0
 **/
public class ArchPathEntity {
    private Integer id;
    private String archNo;
    private String path;
    private Integer delFlag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getArchNo() {
        return archNo;
    }

    public void setArchNo(String archNo) {
        this.archNo = archNo;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }
}
