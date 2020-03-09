package com.xxq.filemanager.bean;

/**
 * @ClassName UserParaInfo
 * @Description: TODO
 * @Author xxq
 * @Date 2020/2/27 13:54
 * @Version V1.0
 **/
public class UserParaInfo {
    private Integer id;
    private String departName;
    private String username;
    private String password;
    private String phone;
    private String email;
    private String role;
    private Integer power;

    @Override
    public String toString() {
        return "UserParaInfo{" +
                "id=" + id +
                ", departName='" + departName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", power=" + power +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }
}
