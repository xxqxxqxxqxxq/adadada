package com.xxq.filemanager.bean;

import com.xxq.filemanager.entity.ArchivesEntity;

import java.util.Date;

/**
 * @ClassName ArchivesInfo
 * @Description: TODO
 * @Author xxq
 * @Date 2020/3/6 18:28
 * @Version V1.0
 **/
public class ArchivesInfo {

    private Integer id;
    /**
     * 档案号
     */
    private Integer archNo;
    /**
     * 档案分类号
     */
    private Integer classId;
    /**
     * 部门号
     */
    private Integer departId;
    /**
     * 档案名称
     */
    private String archivesName;
    /**
     * 借阅状态
     */
    private String borrowStatus;
    /**
     * 档案的类型
     */
    private String archivesType;
    private Date createTime;
    private Date updateTime;
    private String createBy;
    private String updateBy;
    /**
     * 删除标志位
     */
    private Integer delFlag;
    private String checkFlag;
    public static ArchivesInfo createInfoFromEntity(ArchivesEntity archivesEntity){
       ArchivesInfo archivesInfo = new ArchivesInfo();
       archivesInfo.setId(archivesEntity.getId());
       archivesInfo.setClassId(archivesEntity.getClassId());
       archivesInfo.setDepartId(archivesEntity.getDepartId());
       archivesInfo.setArchivesName(archivesEntity.getArchivesName());
       archivesInfo.setArchivesType(archivesEntity.getArchivesType());
       archivesInfo.setBorrowStatus(archivesEntity.getBorrowStatus());
       archivesInfo.setUpdateBy(archivesEntity.getUpdateBy());
       archivesInfo.setCreateBy(archivesEntity.getCreateBy());
        archivesInfo.setCreateTime(archivesEntity.getCreateTime());
        archivesInfo.setUpdateTime(archivesEntity.getUpdateTime());
        archivesInfo.setDelFlag(archivesEntity.getDelFlag());
        archivesInfo.setCheckFlag(archivesEntity.getCheckFlag());
        archivesInfo.setArchNo(archivesEntity.getArchNo());
        return archivesInfo;
    }
    public Integer getId() {
        return id;
    }

    public Integer getArchNo() {
        return archNo;
    }

    public void setArchNo(Integer archNo) {
        this.archNo = archNo;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCheckFlag() {
        return checkFlag;
    }

    public void setCheckFlag(String checkFlag) {
        this.checkFlag = checkFlag;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getDepartId() {
        return departId;
    }

    public void setDepartId(Integer departId) {
        this.departId = departId;
    }

    public String getArchivesName() {
        return archivesName;
    }

    public void setArchivesName(String archivesName) {
        this.archivesName = archivesName;
    }

    public String getBorrowStatus() {
        return borrowStatus;
    }

    public void setBorrowStatus(String borrowStatus) {
        this.borrowStatus = borrowStatus;
    }

    public String getArchivesType() {
        return archivesType;
    }

    public void setArchivesType(String archivesType) {
        this.archivesType = archivesType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }
}
