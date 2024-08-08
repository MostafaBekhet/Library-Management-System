package com.example.library.record;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api")
public class BorrowingRecordController {

    private final BorrowingRecordService borrowingRecordService;

    @Autowired
    public BorrowingRecordController(BorrowingRecordService borrowingRecordService) {
        this.borrowingRecordService = borrowingRecordService;
    }

    @PostMapping(path = "borrow/{bookId}/patron/{patronId}")
    public void borrowBook(@PathVariable("bookId") Long bookId,
                       @PathVariable("patronId") Long patronId) {
        borrowingRecordService.borrowBook(bookId , patronId);
    }

    @PutMapping (path = "return/{bookId}/patron/{patronId}")
    public void returnBook(@PathVariable("bookId") Long bookId,
                           @PathVariable("patronId") Long patronId) {
        borrowingRecordService.returnBook(bookId , patronId);
    }
}
