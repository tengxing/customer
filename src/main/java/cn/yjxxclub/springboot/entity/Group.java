package cn.yjxxclub.springboot.entity;

/**
 * Author: 遇见小星
 * Email: tengxing7452@163.com
 * Date: 17-6-18
 * Time: 下午8:01
 * Describe: 分组
 */
public class Group implements java.io.Serializable {
    private Integer id;
    private Integer value;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
