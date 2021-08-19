package com.study.service;

import com.study.pojo.Book;
import com.study.pojo.Page;

import java.util.List;

/**
 * ClassName: BookService
 * Description:
 * date: 2021/8/4 11:03
 *
 * @author Quensty
 * @since JDK 1.8
 */
public interface BookService {

    void addBook(Book book);
    void deleteBookById(Integer id);
    void updateBook(Book book);
    Book queryBookById(Integer id);
    List<Book> queryBooks();

    Page<Book> page(int pageNo, int pageSize);

    Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max);
}
