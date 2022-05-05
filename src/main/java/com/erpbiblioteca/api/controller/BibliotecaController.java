package com.erpbiblioteca.api.controller;

import com.erpbiblioteca.api.model.Autor;
import com.erpbiblioteca.api.model.Livro;
import com.erpbiblioteca.api.model.Membro;
import com.erpbiblioteca.api.model.request.AutorCreationRequest;
import com.erpbiblioteca.api.model.request.LivroCreationRequest;
import com.erpbiblioteca.api.model.request.LivroAluguelRequest;
import com.erpbiblioteca.api.model.request.MembroCreationRequest;
import com.erpbiblioteca.api.service.BibliotecaService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/biblioteca")
@RequiredArgsConstructor
@CrossOrigin("*")
public class BibliotecaController {

    private final BibliotecaService bibliotecaService;

    @GetMapping("/livro")
    public ResponseEntity listarLivros(@RequestParam(required = false) String serial) {
        if (serial == null) {
            return ResponseEntity.ok(bibliotecaService.listarTodosLivros());
        }
        return ResponseEntity.ok(bibliotecaService.buscarLivroPorSerial(serial));
    }

    @GetMapping("/livro/{livroId}")
    public ResponseEntity<Livro> buscarLivroPorId (@PathVariable Long livroId) {
        return ResponseEntity.ok(bibliotecaService.buscarLivroPorId(livroId));
    }

    @PostMapping("/livro")
    public ResponseEntity<Livro> cadastrarLivro (@RequestBody LivroCreationRequest request) {
        return ResponseEntity.ok(bibliotecaService.cadastrarLivro(request));
    }


    @PatchMapping("/livro/{livroId}")
    public ResponseEntity<Livro> atualizarLivro (@PathVariable("livroId") Long livroId, @RequestBody LivroCreationRequest request) {
        return ResponseEntity.ok(bibliotecaService.atualizarLivro(livroId, request));
    }

    @PostMapping("/autor")
    public ResponseEntity<Autor> cadastrarAutor (@RequestBody AutorCreationRequest request) {
        return ResponseEntity.ok(bibliotecaService.cadastrarAutor(request));
    }

    @GetMapping("/autor")
    public ResponseEntity<List<Autor>> listarAutores () {
        return ResponseEntity.ok(bibliotecaService.listarAutores());
    }

    @DeleteMapping("/livro/{livroId}")
    public ResponseEntity<Void> excluirLivro (@PathVariable Long livroId) {
        bibliotecaService.deletarLivro(livroId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/membro")
    public ResponseEntity<Membro> cadastrarMembro (@RequestBody MembroCreationRequest request) {
        return ResponseEntity.ok(bibliotecaService.cadastrarMembro(request));
    }

    @GetMapping("/membro")
    public ResponseEntity<List<Membro>> listarMembros () {
        return ResponseEntity.ok(bibliotecaService.listarMembros());
    }

    @PatchMapping("/membro/{membroId}")
    public ResponseEntity<Membro> atualizarMembro (@RequestBody MembroCreationRequest request, @PathVariable Long membroId) {
        return ResponseEntity.ok(bibliotecaService.atualizarMembro(membroId, request));
    }

    @PostMapping("/livro/aluguel")
    public ResponseEntity<List<String>> alugarLivro(@RequestBody LivroAluguelRequest livroAluguelRequests) {
        return ResponseEntity.ok(bibliotecaService.alugarUmLivro(livroAluguelRequests));
    }

}
