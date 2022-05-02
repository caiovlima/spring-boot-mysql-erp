package com.erpbiblioteca.api.repository;

import com.erpbiblioteca.api.model.Book;
import com.erpbiblioteca.api.model.Lend;
import com.erpbiblioteca.api.model.LendStatus;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LendRepository extends JpaRepository<Lend, Long> {
    Optional<Lend> findByBookAndStatus(Book book, LendStatus status);
}
