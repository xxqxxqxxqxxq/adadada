package com.xxq.filemanager.bean;

import com.xxq.filemanager.entity.ArchivesParaEntity;

import java.util.Date;

/**
 * @ClassName ArchivesParaEntity
 * @Description: TODO
 * @Author xxq
 * @Date 2020/3/9 19:05
 * @Version V1.0
 **/

public class ArchivesParaInfo {
    private Integer id;
    private Integer archivesId;
    private String name;
    private String gender;
    private Date birthdate;
    private String idCard;
    private String email;
    /**
     * 身份
     */
    private String positions;
    private String address;
    private String phone;
    /**
     * 婚姻状况
     */
    private String maritalStatus;
    /**
     * 宗教信仰
     */
    private String religion;
    /**
     * 民族
     */
    private String nation;
    private String createBy;
    private Date createTime;
    private Date updateTime;
    private String updateBy;
    /**
     * 描述
     */
    private String remark;
    /**
     * 删除标志位
     */
    private Integer delFlag;
    private String political;
    public static ArchivesParaInfo createInfoFromEntity(ArchivesParaEntity paraEntity){
        ArchivesParaInfo paraInfo = new ArchivesParaInfo();
        paraInfo.setId(paraEntity.getId());
        paraInfo.setName(paraEntity.getName());
        paraInfo.setGender(paraEntity.getGender());
        paraInfo.setBirthdate(paraEntity.getBirthdate());
        paraInfo.setIdCard(paraEntity.getIdCard());
        paraInfo.setEmail(paraEntity.getEmail());
        paraInfo.setPositions(paraEntity.getPositions());
        paraInfo.setAddress(paraEntity.getAddress());
        paraInfo.setPhone(paraEntity.getPhone());
        paraInfo.setMaritalStatus(paraEntity.getMaritalStatus());
        paraInfo.setPolitical(paraEntity.getPolitical());
        paraInfo.setReligion(paraEntity.getReligion());
        paraInfo.setNation(paraEntity.getNation());
        paraInfo.setCreateTime(paraEntity.getCreateTime());
        paraInfo.setUpdateTime(paraEntity.getUpdateTime());
        paraInfo.setCreateBy(paraEntity.getCreateBy());
        paraInfo.setUpdateBy(paraEntity.getUpdateBy());
        paraInfo.setRemark(paraEntity.getRemark());
        paraInfo.setDelFlag(paraEntity.getDelFlag());
        paraInfo.setArchivesId(paraEntity.getArchivesId());
        return paraInfo;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPolitical() {
        return political;
    }

    public void setPolitical(String political) {
        this.political = political;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPositions() {
        return positions;
    }

    public void setPositions(String positions) {
        this.positions = positions;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
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

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    @Override
    public String toString() {
        return "ArchivesParaInfo{" +
                "id=" + id +
                ", archivesId=" + archivesId +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", birthdate=" + birthdate +
                ", idCard='" + idCard + '\'' +
                ", email='" + email + '\'' +
                ", positions='" + positions + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", maritalStatus='" + maritalStatus + '\'' +
                ", religion='" + religion + '\'' +
                ", nation='" + nation + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", updateBy='" + updateBy + '\'' +
                ", remark='" + remark + '\'' +
                ", delFlag=" + delFlag +
                ", political='" + political + '\'' +
                '}';
    }
}
