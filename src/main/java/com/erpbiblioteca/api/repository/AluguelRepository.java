package com.erpbiblioteca.api.repository;

import com.erpbiblioteca.api.model.Livro;
import com.erpbiblioteca.api.model.Aluguel;
import com.erpbiblioteca.api.model.AluguelStatus;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AluguelRepository extends JpaRepository<Aluguel, Long> {
    Optional<Aluguel> findByLivroAndStatus(Livro livro, AluguelStatus status);
}
