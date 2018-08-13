package com.demo.orm.example.entity;

import com.demo.orm.core.annotation.Column;
import com.demo.orm.core.annotation.PK;
import com.demo.orm.core.annotation.Table;

import java.io.Serializable;

/**
 * @author setsuna
 *  书
 */
@Table(value = "book_info")
public class BookInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @PK(value = "book_id")
    @Column(value = "book_id")
    private Integer bookId;

    @Column(value = "book_author")
    private String bookAuthor;

    @Column(value = "book_price")
    private Double bookPrice;

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public Double getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(Double bookPrice) {
        this.bookPrice = bookPrice;
    }

    @Override
    public String toString() {
        return "BookInfo{" +
                "bookId=" + bookId +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", bookPrice=" + bookPrice +
                '}';
    }
}
