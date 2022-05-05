package com.erpbiblioteca.api.model.request;

import lombok.Data;

@Data
public class LivroCreationRequest {
    private String nome;
    private String serial;
    private Long autorId;
}
