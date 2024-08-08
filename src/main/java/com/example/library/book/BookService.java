package com.example.library.book;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public boolean checkBookExsistsById(Long bookId) {
        return bookRepository.existsById(bookId);
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long bookId) {

        if(!checkBookExsistsById(bookId)) {
            throw new IllegalStateException("Book with id: " + bookId + " does not exists!");
        }
        return bookRepository.findById(bookId);
    }

    public void addNewBook(Book book) {
        Optional<Book>bookOptional = bookRepository.findBookByBookISBN(book.getBookISBN());
        if(bookOptional.isPresent()) {
            throw new IllegalStateException("A book with same ISBN added before!");
        }

        bookRepository.save(book);
    }

    public void deleteBook(Long bookId) {
        if(!checkBookExsistsById(bookId)) {
            throw new IllegalStateException("Book with id: " + bookId + " does not exists!");
        }

        bookRepository.deleteById(bookId);
    }

    @Transactional
    public void updateBook(Long bookId, String title, String author, Integer pubYear) {
        Book book = bookRepository.findById(bookId).orElseThrow(() ->
                new IllegalStateException("Book with id: " + bookId + " does not exitst!"));

        if(title != null && title.length() > 0 && !Objects.equals(title , book.getTitle())) {
            book.setTitle(title);
        }

        if(author != null && author.length() > 0 && !Objects.equals(author , book.getAuthor())) {
            book.setAuthor(author);
        }

        if(pubYear != null && pubYear > 0 && pubYear != book.getPubYear()) {
            book.setPubYear(pubYear);
        }
    }
}
