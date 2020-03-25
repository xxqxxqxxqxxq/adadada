package com.xxq.filemanager.entity;

import java.util.Date;

/**
 * @ClassName BorrowEntity
 * @Description: TODO
 * @Author xxq
 * @Date 2020/3/22 15:24
 * @Version V1.0
 **/
public class BorrowEntity {
    private Integer id;
    private Integer archivesId;
    private String archName;
    private Integer userId;
    private String BName;
    private Date BDate;
    private Date backTime;
    private String approve;
    private Integer lendFlag;
    private String checkFlag;

    public String getArchName() {
        return archName;
    }

    public void setArchName(String archName) {
        this.archName = archName;
    }

    public String getCheckFlag() {
        return checkFlag;
    }

    public void setCheckFlag(String checkFlag) {
        this.checkFlag = checkFlag;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArchivesId() {
        return archivesId;
    }

    public void setArchivesId(Integer archivesId) {
        this.archivesId = archivesId;
    }

    public String getBName() {
        return BName;
    }

    public void setBName(String BName) {
        this.BName = BName;
    }

    public Date getBDate() {
        return BDate;
    }

    public void setBDate(Date BDate) {
        this.BDate = BDate;
    }

    public Date getBackTime() {
        return backTime;
    }

    public void setBackTime(Date backTime) {
        this.backTime = backTime;
    }

    public String getApprove() {
        return approve;
    }

    public void setApprove(String approve) {
        this.approve = approve;
    }

    public Integer getLendFlag() {
        return lendFlag;
    }

    public void setLendFlag(Integer lendFlag) {
        this.lendFlag = lendFlag;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
