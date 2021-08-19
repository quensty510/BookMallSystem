package com.study.dao;

import com.study.pojo.Book;

import java.util.List;

/**
 * ClassName: BookDao
 * Description:
 * date: 2021/8/4 10:32
 *
 * @author Quensty
 * @since JDK 1.8
 */
public interface BookDao {

    int addBook(Book book);
    int deleteBook(Integer id);
    int updateBook(Book book);
    Book queryBookById(Integer id);
    List<Book> queryBooks();

    Integer queryForPageTotalCount();
    List<Book> queryForPageItems(int begin, int pageSize);

    Integer queryForPageTotalCountByPrice(int min,int max);

    List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max);
}
