package com.study.test;

import com.study.pojo.Book;
import com.study.service.BookService;
import com.study.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * ClassName: BookServiceTest
 * Description:
 * date: 2021/8/4 11:08
 *
 * @author Quensty
 * @since JDK 1.8
 */
public class BookServiceTest {
    private BookService bookService = new BookServiceImpl();
    @Test
    public void addBook() {
        bookService.addBook(new Book(null,"按规定改","quensty",new BigDecimal(999),222,222,null));
    }

    @Test
    public void deleteBookById() {
        bookService.deleteBookById(24);
    }

    @Test
    public void updateBook() {
        bookService.updateBook(new Book(24,"社会主义好","quensty",new BigDecimal(999),222,222,null));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookService.queryBookById(24));
    }

    @Test
    public void queryBooks() {
        for (Book book : bookService.queryBooks()){
            System.out.println(book);
        }
    }

    @Test
    public void pageTest(){
        System.out.println(bookService.page(0, 4));
    }

    @Test
    public void pageByPriceTest(){
        System.out.println(bookService.pageByPrice(0, 4,10,80));
    }
}