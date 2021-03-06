package cn.yjxxclub.springboot.entity;

import java.util.Date;

/**
 * Author: 遇见小星
 * Email: tengxing7452@163.com
 * Date: 17-6-7
 * Time: 下午4:41
 * Describe: 行业
 */
public class BmIndustry implements java.io.Serializable {
    private Integer id;
    private String name;
    private Integer status;
    private Date updateDate;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "BmIndustry{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", updateDate=" + updateDate +
                ", createDate=" + createDate +
                '}';
    }
}

