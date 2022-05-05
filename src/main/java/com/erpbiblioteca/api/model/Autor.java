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
@Table(name = "autor")
@NoArgsConstructor
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String primeiroNome;

    private String segundoNome;

    @JsonBackReference
    @OneToMany(mappedBy = "autor",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Livro> livros;

}