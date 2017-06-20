package cn.yjxxclub.springboot.entity;

import java.util.Date;
import java.util.List;

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
    private User user;
    private String phoneNumber;
    private String address;
    private BmIndustry bmIndustry;
    private BmArea bmArea;
    private BmType bmType;
    private BmStatus bmStatus;
    private Contact contact;
    private List<ContactNotes>  contactNotes;
    private Integer status;
    private Date updateDate;
    private String creator;
    private Date createDate;

    public List<ContactNotes> getContactNotes() {
        return contactNotes;
    }

    public void setContactNotes(List<ContactNotes> contactNotes) {
        this.contactNotes = contactNotes;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public BmIndustry getBmIndustry() {
        return bmIndustry;
    }

    public void setBmIndustry(BmIndustry bmIndustry) {
        this.bmIndustry = bmIndustry;
    }

    public BmArea getBmArea() {
        return bmArea;
    }

    public void setBmArea(BmArea bmArea) {
        this.bmArea = bmArea;
    }

    public BmType getBmType() {
        return bmType;
    }

    public void setBmType(BmType bmType) {
        this.bmType = bmType;
    }

    public BmStatus getBmStatus() {
        return bmStatus;
    }

    public void setBmStatus(BmStatus bmStatus) {
        this.bmStatus = bmStatus;
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

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", user=" + user +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", bmIndustry=" + bmIndustry +
                ", bmArea=" + bmArea +
                ", bmType=" + bmType +
                ", bmStatus=" + bmStatus +
                ", status=" + status +
                ", updateDate=" + updateDate +
                ", creator='" + creator + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
