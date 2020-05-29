package com.xxq.filemanager.bean;

import com.xxq.filemanager.entity.SysUserEntity;

import java.util.Date;

/**
 * @ClassName SysUserInfo
 * @Description: TODO
 * @Author xxq
 * @Date 2020/1/16 15:18
 * @Version V1.0
 **/
public class SysUserInfo {
    private Integer id;
    private Integer departId;
    private String username;
    private  String password;
    /**
     * 权限等级
     */
    private Integer power;
    private String departName;
    /**
     * 角色
     */
    private String role;
    private Date createTime;
    private String updateBy;
    private Date updateTime;
    private String phone;
    private String email;
    private Integer delFlag;
    private Integer loginFlag;
    /**
     * 登陆时间
     */
    private long time;

    /**
     * 用来判断tableView有没有被选中
     */
    private String checkFlag;
    private String photo;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }

    public Integer getLoginFlag() {
        return loginFlag;
    }

    public void setLoginFlag(Integer loginFlag) {
        this.loginFlag = loginFlag;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static SysUserInfo createFromEntity(SysUserEntity entity) {
        SysUserInfo userInfo = new SysUserInfo();
        userInfo.setId(entity.getId());
        userInfo.setUsername(entity.getUsername());
        userInfo.setTime(System.currentTimeMillis());
        userInfo.setPassword(entity.getPassword());
        userInfo.setPower(entity.getPower());
        userInfo.setRole(entity.getRole());
        userInfo.setPhone(entity.getPhone());
        userInfo.setEmail(entity.getEmail());
        userInfo.setDepartName(entity.getDepartName());
        userInfo.setLoginFlag(entity.getLoginFlag());
        userInfo.setCreateTime(entity.getCreateTime());
        userInfo.setUpdateBy(entity.getUpdateBy());
        userInfo.setUpdateTime(entity.getUpdateTime());
        userInfo.setCheckFlag(entity.getCheckFlag());
        userInfo.setDepartId(entity.getDepartId());
        userInfo.setPhoto(entity.getPhoto());
        return userInfo;
    }

    public Integer getDepartId() {
        return departId;
    }

    public void setDepartId(Integer departId) {
        this.departId = departId;
    }

    public String getCheckFlag() {
        return checkFlag;
    }

    @Override
    public String toString() {
        return "SysUserInfo{" +
                "id=" + id +
                ", departId=" + departId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", power=" + power +
                ", departName='" + departName + '\'' +
                ", role='" + role + '\'' +
                ", createTime=" + createTime +
                ", updateBy='" + updateBy + '\'' +
                ", updateTime=" + updateTime +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", delFlag=" + delFlag +
                ", loginFlag=" + loginFlag +
                ", time=" + time +
                ", checkFlag='" + checkFlag + '\'' +
                '}';
    }

    public void setCheckFlag(String checkFlag) {
        this.checkFlag = checkFlag;
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

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }


}
