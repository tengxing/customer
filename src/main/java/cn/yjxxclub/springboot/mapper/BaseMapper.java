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
    /**
     * 通过id删除
     * @param id
     * @return
     */
    Integer deleteById(Integer id);

    /**
     * 更新
     * @param obj
     * @return
     */
    Integer update(T obj);

    /**
     * 保存
     * @param obj
     * @return
     */
    Integer save(T obj);

    /**
     * 通过id查找
     * @param id
     * @return
     */
    T findById(Integer id);

    /**
     * 所有条数
     * @return
     */
    Integer countTotal();

    /**
     * 分页
     * @param map
     * @return
     */
    List<T> list(Map<String,Object> map);
}
