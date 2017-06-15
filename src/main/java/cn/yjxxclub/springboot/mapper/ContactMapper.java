package cn.yjxxclub.springboot.mapper;

import cn.yjxxclub.springboot.entity.Contact;
import org.apache.ibatis.annotations.Mapper;

/**
 * Author: 遇见小星
 * Email: tengxing7452@163.com
 * Date: 17-6-15
 * Time: 下午8:55
 * Describe: 联系人Mapper
 */
@Mapper
public interface ContactMapper extends BaseMapper<Contact> {

}
