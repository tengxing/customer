package cn.yjxxclub.springboot.entity;

import java.util.Date;

/**
 * Author: 遇见小星
 * Email: tengxing7452@163.com
 * Date: 17-6-15
 * Time: 下午8:45
 * Describe: 联系记录
 */
public class ContactNotes implements java.io.Serializable{

    private Integer id;
    private Integer memberId;
    private String contactType;
    private String description;
    private Date contactTime;
    private Integer status;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getContactType() {
        return contactType;
    }

    public void setContactType(String contactType) {
        this.contactType = contactType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getContactTime() {
        return contactTime;
    }

    public void setContactTime(Date contactTime) {
        this.contactTime = contactTime;
    }

    @Override
    public String toString() {
        return "ContactNotes{" +
                "id=" + id +
                ", memberId=" + memberId +
                ", contactType='" + contactType + '\'' +
                ", description='" + description + '\'' +
                ", contactTime=" + contactTime +
                ", status=" + status +
                ", name='" + name + '\'' +
                '}';
    }
}
