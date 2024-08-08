package com.example.library.book;

import com.example.library.record.BorrowingRecord;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class Book {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String author;
    private Integer pubYear;
    private String bookISBN;

    @OneToMany(mappedBy = "book" , cascade = CascadeType.ALL)
    private List<BorrowingRecord> borrowingRecords;

    public Book() {
    }

    public Book(Long id, String title, String author, Integer pubYear, String bookISBN) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.pubYear = pubYear;
        this.bookISBN = bookISBN;
    }

    public Book(String title, String author, Integer pubYear, String bookISBN) {
        this.title = title;
        this.author = author;
        this.pubYear = pubYear;
        this.bookISBN = bookISBN;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getPubYear() {
        return pubYear;
    }

    public void setPubYear(Integer pubYear) {
        this.pubYear = pubYear;
    }

    public String getBookISBN() {
        return bookISBN;
    }

    public void setBookISBN(String bookISBN) {
        this.bookISBN = bookISBN;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", pubYear=" + pubYear +
                ", bookISBN='" + bookISBN + '\'' +
                '}';
    }
}
