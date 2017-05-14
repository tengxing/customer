package cn.yjxxclub.springboot.service;

import cn.yjxxclub.springboot.entity.Book;

import java.util.List;

/**
 * Author: 遇见小星
 * Email: tengxing7452@163.com
 * Date: 17-5-4
 * Time: 下午9:50
 * Describe: Book Service层接口
 */
public interface BookService {

    /**
     * 所有书籍
     * @return
     */
    List<Book> list();

    /**
     * 修改或保存书籍
     * @param book
     * @return
     */
    Integer saveOrupdate(Book book);

    /**
     * 删除书籍通过id
     * @param id
     * @return
     */
    Integer deleteById(Integer id);

    /**
     * 找书籍通过id
     * @param id
     * @return
     */
    Book findById(Integer id);
}


