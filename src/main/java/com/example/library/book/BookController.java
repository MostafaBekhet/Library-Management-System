package com.example.library.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getBooks() {
        return bookService.getBooks();
    }

    @GetMapping(path = "{bookId}")
    public Optional<Book> getBooks(@PathVariable("bookId") Long bookId) {
        return bookService.getBookById(bookId);
    }

    @PostMapping
    public void addBook(@RequestBody Book book) {
        if(book.getBookISBN() == null || book.getBookISBN().length() == 0) {
            throw new IllegalStateException("Not valid ISBN!");
        }
        bookService.addNewBook(book);
    }

    @PutMapping(path = "{bookId}")
    public void updateBook(@PathVariable("bookId") Long bookId,
                           @RequestParam(required = false) String title,
                           @RequestParam(required = false) String author,
                           @RequestParam(required = false) Integer pubYear) {
        bookService.updateBook(bookId , title , author , pubYear);
    }

    @DeleteMapping(path = "{bookId}")
    public void deleteBook(@PathVariable("bookId") Long bookId) {
        bookService.deleteBook(bookId);
    }
}
