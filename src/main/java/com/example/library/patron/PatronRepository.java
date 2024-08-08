package com.example.library.patron;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatronRepository extends JpaRepository<Patron , Long> {
    Optional<Patron> findByEmail(String email);
}
