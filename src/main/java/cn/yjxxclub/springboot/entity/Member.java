package cn.yjxxclub.springboot.entity;

import java.util.Date;

/**
 * Author: 遇见小星
 * Email: tengxing7452@163.com
 * Date: 17-6-7
 * Time: 下午4:49
 * Describe: 客户
 */
public class Member implements java.io.Serializable{

    private Integer id;
    private String name;
    private Integer managedId;
    private String phoneNumber;
    private String address;
    private Integer bmIndustryId;
    private Integer bmAreaId;
    private Integer bmTypeId;
    private Integer bmStatusId;
    private Integer status;
    private Date updateDate;
    private String creator;
    private Date createDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getManagedId() {
        return managedId;
    }

    public void setManagedId(Integer managedId) {
        this.managedId = managedId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getBmIndustryId() {
        return bmIndustryId;
    }

    public void setBmIndustryId(Integer bmIndustryId) {
        this.bmIndustryId = bmIndustryId;
    }

    public Integer getBmAreaId() {
        return bmAreaId;
    }

    public void setBmAreaId(Integer bmAreaId) {
        this.bmAreaId = bmAreaId;
    }

    public Integer getBmTypeId() {
        return bmTypeId;
    }

    public void setBmTypeId(Integer bmTypeId) {
        this.bmTypeId = bmTypeId;
    }

    public Integer getBmStatusId() {
        return bmStatusId;
    }

    public void setBmStatusId(Integer bmStatusId) {
        this.bmStatusId = bmStatusId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
