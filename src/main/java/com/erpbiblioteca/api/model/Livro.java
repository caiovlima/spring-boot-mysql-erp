package com.erpbiblioteca.api.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "livro")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String serial;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    @JsonManagedReference
    private Autor autor;

    @JsonBackReference
    @OneToMany(mappedBy = "livro",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Aluguel> aluguels;


}
