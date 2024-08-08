package com.example.library.record;

import com.example.library.book.Book;
import com.example.library.patron.Patron;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord , Long> {

    boolean existsByBookAndReturnDateIsNull(Optional<Book> book);

    Optional<BorrowingRecord> findBorrowingRecordByPatronAndBookAndReturnDateIsNull(Optional<Patron> patron , Optional<Book> book);
}
