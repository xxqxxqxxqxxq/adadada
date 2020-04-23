package com.xxq.filemanager.entity;

import java.util.Date;

/**
 * @ClassName FileTypeEntity
 * @Description: TODO
 * @Author xxq
 * @Date 2020/3/27 18:30
 * @Version V1.0
 **/
public class FileTypeEntity {
    private Integer id;
    private String type;
    private Date createTime;
    private String createBy;
    private Integer delFlag;
    private String checkFlag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getCheckFlag() {
        return checkFlag;
    }

    public void setCheckFlag(String checkFlag) {
        this.checkFlag = checkFlag;
    }
}
