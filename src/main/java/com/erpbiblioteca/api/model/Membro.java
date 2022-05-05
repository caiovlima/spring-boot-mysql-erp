package com.erpbiblioteca.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.List;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "membro")
@NoArgsConstructor
public class Membro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String primeiroNome;
    private String segundoNome;

    @Enumerated(EnumType.STRING)
    private MembroStatus status;

    @JsonBackReference
    @OneToMany(mappedBy = "membro",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Aluguel> alugueis;

}
