package cn.yjxxclub.springboot.mapper;

import cn.yjxxclub.springboot.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * Author: 遇见小星
 * Email: tengxing7452@163.com
 * Date: 17-6-11
 * Time: 上午10:03
 * Describe: 客户经理Mapper
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
