package com.erpbiblioteca.api.repository;

import com.erpbiblioteca.api.model.Membro;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MembroRepository extends JpaRepository<Membro, Long> {
}
