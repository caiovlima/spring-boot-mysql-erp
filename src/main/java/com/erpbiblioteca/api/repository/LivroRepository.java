package com.erpbiblioteca.api.repository;

import com.erpbiblioteca.api.model.Livro;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    Optional<Livro> findBySerial(String serial);
}
