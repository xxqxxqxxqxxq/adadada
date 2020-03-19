package com.xxq.filemanager.entity;

import com.xxq.filemanager.bean.ArchivesInfo;

import java.util.Date;

/**
 * @ClassName ArchivesEntity
 * @Description: 档案对应的实体类
 * @Author xxq
 * @Date 2020/3/5 19:31
 * @Version V1.0
 **/
public class ArchivesEntity {

    private Integer id;
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
    private String checkFlag;
    /**
     * 删除标志位
     */
    private Integer delFlag;
    public static ArchivesEntity createEntityFromInfo(ArchivesInfo archivesInfo){
        ArchivesEntity archivesEntity= new ArchivesEntity();
        archivesEntity.setArchivesName(archivesInfo.getArchivesName());
        archivesEntity.setArchivesType(archivesInfo.getArchivesType());
        archivesEntity.setBorrowStatus(archivesInfo.getBorrowStatus());
        archivesEntity.setId(archivesInfo.getId());
        archivesEntity.setClassId(archivesInfo.getClassId());
        archivesEntity.setDepartId(archivesInfo.getDepartId());
        archivesEntity.setUpdateBy(archivesInfo.getUpdateBy());
        archivesEntity.setCreateBy(archivesInfo.getCreateBy());
        archivesEntity.setCreateTime(archivesInfo.getCreateTime());
        archivesEntity.setUpdateTime(archivesInfo.getUpdateTime());
        archivesEntity.setCheckFlag(archivesInfo.getCheckFlag());
        archivesEntity.setDelFlag(archivesInfo.getDelFlag());
        archivesEntity.setArchNo(archivesInfo.getArchNo());

        return archivesEntity;
    }
    public Integer getArchNo() {
        return archNo;
    }

    public void setArchNo(Integer archNo) {
        this.archNo = archNo;
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
