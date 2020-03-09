package com.xxq.filemanager.entity;

import com.xxq.filemanager.bean.SysUserInfo;

import java.util.Date;

/**
 * @ClassName SysUserEntity
 * @Description: TODO
 * @Author xxq
 * @Date 2020/1/16 14:33
 * @Version V1.0
 **/
public class SysUserEntity {
    /**
     * 工号
     */
    private Integer id;
    private Integer departId;
    private String departName;

    /**
     * 用户名
     */
    private String username;
    private String password;
    /**
     * 权限
     */
    private Integer power;
    /**
     * 用户角色
     */
    private String role;
    private Date createTime;
    private String updateBy;
    private Date updateTime;
    private String phone;
    private String email;



    private Integer delFlag;
    private Integer loginFlag;
    private String checkFlag;


    public static SysUserEntity createFromInfo(SysUserInfo sysUserInfo) {
        SysUserEntity sysUserEntity = new SysUserEntity();
        sysUserEntity.setId(sysUserInfo.getId());
        sysUserEntity.setUsername(sysUserInfo.getUsername());
        sysUserEntity.setPower(sysUserInfo.getPower());
        sysUserEntity.setRole(sysUserInfo.getRole());
        sysUserEntity.setPassword(sysUserInfo.getPassword());
        sysUserEntity.setDepartName(sysUserInfo.getDepartName());
        sysUserEntity.setCreateTime(sysUserInfo.getCreateTime());
        sysUserEntity.setUpdateBy(sysUserInfo.getUpdateBy());
        sysUserEntity.setUpdateTime(sysUserInfo.getUpdateTime());
        sysUserEntity.setDelFlag(sysUserInfo.getDelFlag());
        sysUserEntity.setLoginFlag(sysUserInfo.getLoginFlag());
        sysUserEntity.setCheckFlag(sysUserInfo.getCheckFlag());
        sysUserEntity.setDepartId(sysUserInfo.getDepartId());
        sysUserEntity.setPhone(sysUserInfo.getPhone());
        sysUserEntity.setEmail(sysUserInfo.getEmail());
        return sysUserEntity;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getCheckFlag() {
        return checkFlag;
    }

    public void setCheckFlag(String checkFlag) {
        this.checkFlag = checkFlag;
    }

    public Integer getDepartId() {
        return departId;
    }

    public void setDepartId(Integer departId) {
        this.departId = departId;
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public SysUserEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public Integer getLoginFlag() {
        return loginFlag;
    }

    public void setLoginFlag(Integer loginFlag) {
        this.loginFlag = loginFlag;
    }

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }

    public SysUserEntity() {
    }

    @Override
    public String toString() {
        return "SysUserEntity{" +
                "id=" + id +
                ", departId=" + departId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", power=" + power +
                ", role='" + role + '\'' +
                ", createTime=" + createTime +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime=" + updateTime +
                ", phone='" + phone + '\'' +
                ", delFlag=" + delFlag +
                ", loginFlag=" + loginFlag +
                '}';
    }
}
