package com.xxq.filemanager.entity;

import java.util.Date;

/**
 * @ClassName :ArchTypeEntity
 * @Description:
 * @Author xxq
 * @Date 2020/4/3  15:30
 * @Version V1.0
 **/
public class ArchTypeEntity {
    private Integer id;
    private String type;
    private Integer typeNo;
    private Date createTime;
    private Date updateTime;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public Integer getTypeNo() {
        return typeNo;
    }

    public void setTypeNo(Integer typeNo) {
        this.typeNo = typeNo;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
