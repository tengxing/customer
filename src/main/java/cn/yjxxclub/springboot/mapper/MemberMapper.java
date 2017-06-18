package cn.yjxxclub.springboot.mapper;

import cn.yjxxclub.springboot.entity.Group;
import cn.yjxxclub.springboot.entity.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Author: 遇见小星
 * Email: tengxing7452@163.com
 * Date: 17-6-10
 * Time: 上午9:02
 * Describe: 客户Mapper
 */
@Mapper
public interface MemberMapper extends BaseMapper<Member> {
    List<Member> findListByName(String name);
    List<Group> memberByStatus();
}
