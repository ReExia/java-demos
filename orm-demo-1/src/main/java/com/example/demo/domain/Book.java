package com.example.demo.domain;

import com.example.core.annotations.Column;
import com.example.core.annotations.ID;
import com.example.core.annotations.Table;

@Table("t_book")
public class Book {

    @ID(value = "book_id")
    @Column(value = "book_id")
    private String bookid;

    @Column(value = "book_name")
    private String bookName;

    @Column(value = "author")
    private String author;

    public String getBookid() {
        return bookid;
    }

    public void setBookid(String bookid) {
        this.bookid = bookid;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookid='" + bookid + '\'' +
                ", bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
