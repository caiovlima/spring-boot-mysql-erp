package com.erpbiblioteca.api.repository;

import com.erpbiblioteca.api.model.Member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
