package com.study.test;

import com.study.dao.BookDao;
import com.study.dao.impl.BookDaoImpl;
import com.study.pojo.Book;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * ClassName: BookDaoTest
 * Description:
 * date: 2021/8/4 10:52
 *
 * @author Quensty
 * @since JDK 1.8
 */
public class BookDaoTest {
    private BookDao bookDao = new BookDaoImpl();
    @Test
    public void addBook() {
        bookDao.addBook(new Book(null,"哗啦哗啦","quensty",new BigDecimal(999),999,999,null));
    }

    @Test
    public void deleteBook() {
        bookDao.deleteBook(23);
    }

    @Test
    public void updateBook() {
        bookDao.updateBook(new Book(23,"马诺","quensty",new BigDecimal(999),999,999,null));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookDao.queryBookById(23));
    }

    @Test
    public void queryBook() {
        for(Book book : bookDao.queryBooks()){
            System.out.println(book);
        }
    }
    @Test
    public void queryFroPageTotalCountTest() {
        System.out.println(bookDao.queryForPageTotalCount());
    }

    @Test
    public void queryFroPageItemsTest() {
        for (Book queryFroPageItem : bookDao.queryForPageItems(0, 4)) {
            System.out.println(queryFroPageItem);
        }
    }
    @Test
    public void queryFroPageTotalCountByPriceTest() {
        System.out.println(bookDao.queryForPageTotalCountByPrice(10,80));
    }

    @Test
    public void queryFroPageItemsByPriceTest() {
        for (Book queryFroPageItem : bookDao.queryForPageItemsByPrice(0, 4,10,80)) {
            System.out.println(queryFroPageItem);
        }
    }
}