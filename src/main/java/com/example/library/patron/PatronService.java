package com.example.library.patron;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PatronService {

    private final PatronRepository patronRepository;

    @Autowired
    public PatronService(PatronRepository patronRepository) {
        this.patronRepository = patronRepository;
    }

    public List<Patron> getAllPatrons() {
        return patronRepository.findAll();
    }

    public boolean checkPatronExsistsById(Long patronId) {
        return patronRepository.existsById(patronId);
    }

    public Optional<Patron> getPatronById(Long patronId) {

        if(!checkPatronExsistsById(patronId)) {
            throw new IllegalStateException("Patron with id: " + patronId + " does not exists!");
        }

        return patronRepository.findById(patronId);
    }

    public boolean patronWithEmail(String email) {
        Optional<Patron> optionalPatron = patronRepository.findByEmail(email);

        if(optionalPatron.isPresent())
            return true;
        return false;
    }

    public void addNewPatron(Patron patron) {

        if(patronWithEmail(patron.getEmail())) {
            throw new IllegalStateException("This email: " + patron.getEmail() + " already registered!");
        }

        patronRepository.save(patron);
    }

    @Transactional
    public void updatePatron(Long patronId, String name, String email, String phoneNum) {

        Patron patron = patronRepository.findById(patronId).orElseThrow(() ->
                new IllegalStateException("Patron with id: " + patronId + " does not exists!"));

        if(name != null && name.length() > 0 && !Objects.equals(email , patron.getName())) {
            patron.setName(name);
        }

        if(email != null && email.length() > 0 && !Objects.equals(email , patron.getEmail())) {
            if(patronWithEmail(email)) {
                throw new IllegalStateException("This email: " + email + " already registered!");
            }

            patron.setEmail(email);
        }

        if(phoneNum != null && phoneNum.length() > 0 && !Objects.equals(phoneNum , patron.getPhoneNum())) {
            patron.setPhoneNum(phoneNum);
        }
    }

    public void deletePatronById(Long patronId) {

        if(!checkPatronExsistsById(patronId)) {
            throw new IllegalStateException("Patron with id: " + patronId + " does not exists!");
        }

        patronRepository.deleteById(patronId);
    }
}
