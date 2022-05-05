package com.erpbiblioteca.api.model.request;

import java.util.List;

import lombok.Data;

@Data
public class LivroAluguelRequest {
    private List<Long> livroIds;
    private Long membroId;
}
