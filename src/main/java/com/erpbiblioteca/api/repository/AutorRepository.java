package com.erpbiblioteca.api.repository;

import com.erpbiblioteca.api.model.Autor;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Long> {
}
