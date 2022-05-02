package com.erpbiblioteca.api.repository;

import com.erpbiblioteca.api.model.Author;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
