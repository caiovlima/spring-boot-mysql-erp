package com.erpbiblioteca.api.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.time.Instant;
import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "aluguel")
@NoArgsConstructor
public class Aluguel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    private AluguelStatus status;

    private Instant retiradoEm;
    private Instant entregueEm;

    @ManyToOne
    @JoinColumn(name = "aluguel_id")
    @JsonManagedReference
    private Livro livro;

    @ManyToOne
    @JoinColumn(name = "membro_id")
    @JsonManagedReference
    private Membro membro;



}
