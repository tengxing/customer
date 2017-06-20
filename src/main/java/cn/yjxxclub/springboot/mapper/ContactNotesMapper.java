package cn.yjxxclub.springboot.mapper;

import cn.yjxxclub.springboot.entity.ContactNotes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Author: 遇见小星
 * Email: tengxing7452@163.com
 * Date: 17-6-15
 * Time: 下午10:26
 * Describe: 联系记录Mapper
 */
@Mapper
public interface ContactNotesMapper extends BaseMapper<ContactNotes>{
    List<ContactNotes> selectAllByMemberId(Integer id);

}
