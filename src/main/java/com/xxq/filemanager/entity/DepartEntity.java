package com.xxq.filemanager.entity;

import com.xxq.filemanager.bean.DepartInfo;

/**
 * @ClassName DepartEntity
 * @Description: 机构表对应业务层
 * @Author xxq
 * @Date 2020/2/26 17:22
 * @Version V1.0
 **/
public class DepartEntity {
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

    public static DepartEntity createFromInfo(DepartInfo departInfo){
        DepartEntity departEntity = new DepartEntity();
        departEntity.setId(departInfo.getId());
        departEntity.setDepartName(departInfo.getDepartName());
        departEntity.setAmount(departInfo.getAmount());
        departEntity.setDelFlag(departInfo.getDelFlag());
        return departEntity;
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
