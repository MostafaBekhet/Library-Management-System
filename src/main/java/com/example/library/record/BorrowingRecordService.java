package com.example.library.record;

import com.example.library.book.Book;
import com.example.library.book.BookService;
import com.example.library.patron.Patron;
import com.example.library.patron.PatronService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class BorrowingRecordService {

    private final BorrowingRecordRepository borrowingRecordRepository;
    private final BookService bookService;
    private final PatronService patronService;

    @Autowired
    public BorrowingRecordService(BorrowingRecordRepository borrowingRecordRepository, BookService bookService, PatronService patronService) {
        this.borrowingRecordRepository = borrowingRecordRepository;
        this.bookService = bookService;
        this.patronService = patronService;
    }

    public void borrowBook(Long bookId, Long patronId) {

        Optional<Patron> patron = patronService.getPatronById(patronId);
        Optional<Book> book = bookService.getBookById(bookId);

        boolean bookIsBorrowed = borrowingRecordRepository.existsByBookAndReturnDateIsNull(book);

        if(bookIsBorrowed) {
            throw new IllegalStateException("Book with id: " + bookId + " is already borrowed!");
        }

        BorrowingRecord borrowingRecord = new BorrowingRecord();

        Patron pt = patron.map(p -> { return p; }).orElseThrow(() -> new IllegalStateException(""));
        Book bk = book.map(p -> { return p; }).orElseThrow(() -> new IllegalStateException(""));

        borrowingRecord.setPatron(pt);
        borrowingRecord.setBook(bk);
        borrowingRecord.setBorrowDate(LocalDate.now());

        borrowingRecordRepository.save(borrowingRecord);
    }

    @Transactional
    public void returnBook(Long bookId, Long patronId) {
        Optional<Patron> patron = patronService.getPatronById(patronId);
        Optional<Book> book = bookService.getBookById(bookId);

        boolean bookIsBorrowed = borrowingRecordRepository.existsByBookAndReturnDateIsNull(book);

        if(!bookIsBorrowed) {
            throw new IllegalStateException("Book with id: " + bookId + " is not borrowed!");
        }

        Optional<BorrowingRecord> optionalBorrowingRecord =
                borrowingRecordRepository.findBorrowingRecordByPatronAndBookAndReturnDateIsNull(patron , book);

        if(!optionalBorrowingRecord.isPresent()) {
            throw new IllegalStateException("Borrow record with patron id: " + patronId + " and book id: " + bookId + " does not exists!");
        }

        BorrowingRecord borrowingRecord = optionalBorrowingRecord.map(p -> { return p; }).orElseThrow(() -> new IllegalStateException(""));
        borrowingRecord.setReturnDate(LocalDate.now());
    }
}
