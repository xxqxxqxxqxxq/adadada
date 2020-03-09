package com.xxq.filemanager.bean;

import com.xxq.filemanager.entity.DepartEntity;

/**
 * @ClassName DepartInfo
 * @Description: TODO
 * @Author xxq
 * @Date 2020/2/26 17:25
 * @Version V1.0
 **/
public class DepartInfo {
    /**
     * 部门编号
     */
    private Integer id;

    /**
     * 部门名称
     */
    private String departName;

    private Integer amount;

    private Integer delFlag;
    public static DepartInfo createFromEntity(DepartEntity departEntity){
        DepartInfo departInfo = new DepartInfo();
        departInfo.setId(departEntity.getId());
        departInfo.setDepartName(departEntity.getDepartName());
        departInfo.setAmount(departEntity.getAmount());
        departInfo.setDelFlag(departEntity.getDelFlag());
        return departInfo;
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

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }
}
