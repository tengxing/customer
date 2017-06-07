package cn.yjxxclub.springboot.mapper;

import java.util.List;
import java.util.Map;

/**
 * Author: 遇见小星
 * Email: tengxing7452@163.com
 * Date: 17-6-7
 * Time: 下午8:36
 * Describe: 公共mapper
 */
public interface BaseMapper<T> {
    Integer deleteById(Integer id);
    Integer update(T obj);
    Integer save(T obj);
    T findById(Integer id);
    Integer countTotal();
    List<T> list(Map<String,Object> map);
}
