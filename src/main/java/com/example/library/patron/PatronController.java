package com.example.library.patron;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/patrons")
public class PatronController {

    private final PatronService patronService;

    @Autowired
    public PatronController(PatronService patronService) {
        this.patronService = patronService;
    }

    @GetMapping
    public List<Patron> getPatrons() {
        return patronService.getAllPatrons();
    }

    @GetMapping(path = "{patronId}")
    public Optional<Patron> getPatronById(@PathVariable("patronId") Long patronId) {
        return patronService.getPatronById(patronId);
    }

    @PostMapping
    public void registerNewPatron(@RequestBody Patron patron) {
        if(patron.getEmail() == null || patron.getEmail().length() == 0) {
            throw new IllegalStateException("Invalid email!");
        }
        patronService.addNewPatron(patron);
    }

    @PutMapping(path = "{patronId}")
    public void updatePatron(@PathVariable("patronId") Long patronId,
                             @RequestParam(required = false) String name,
                             @RequestParam(required = false) String email,
                             @RequestParam(required = false) String phoneNum) {
        patronService.updatePatron(patronId , name , email , phoneNum);
    }

    @DeleteMapping(path = "{patronId}")
    public void deletePatron(@PathVariable("patronId") Long patronId) {
        patronService.deletePatronById(patronId);
    }
}
