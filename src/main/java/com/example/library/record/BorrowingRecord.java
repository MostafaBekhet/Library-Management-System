package com.example.library.record;

import com.example.library.book.Book;
import com.example.library.patron.Patron;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table
public class BorrowingRecord {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patron_id")
    private Patron patron;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    private LocalDate borrowDate;
    private LocalDate returnDate;

    public BorrowingRecord() {
    }

    public BorrowingRecord(Long id, Patron patron, Book book, LocalDate borrowDate, LocalDate returnDate) {
        this.id = id;
        this.patron = patron;
        this.book = book;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public BorrowingRecord(Patron patron, Book book, LocalDate borrowDate, LocalDate returnDate) {
        this.patron = patron;
        this.book = book;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public Patron getPatron() {
        return patron;
    }

    public void setPatron(Patron patron) {
        this.patron = patron;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}
