package com.erpbiblioteca.api.service;

import com.erpbiblioteca.api.model.*;
import com.erpbiblioteca.api.model.request.AutorCreationRequest;
import com.erpbiblioteca.api.model.request.LivroCreationRequest;
import com.erpbiblioteca.api.model.request.LivroAluguelRequest;
import com.erpbiblioteca.api.model.request.MembroCreationRequest;
import com.erpbiblioteca.api.repository.AutorRepository;
import com.erpbiblioteca.api.repository.LivroRepository;
import com.erpbiblioteca.api.repository.AluguelRepository;
import com.erpbiblioteca.api.repository.MembroRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BibliotecaService {

    private final AutorRepository autorRepository;
    private final MembroRepository membroRepository;
    private final AluguelRepository aluguelRepository;
    private final LivroRepository livroRepository;

    public Livro buscarLivroPorId(Long id) {
        Optional<Livro> livro = livroRepository.findById(id);
        if (livro.isPresent()) {
            return livro.get();
        }
        throw new EntityNotFoundException("Ops! Não foi possível encontrar nenhum livro com esse ID");
    }

    public List<Livro> listarTodosLivros() {
        return livroRepository.findAll();
    }

    public Livro buscarLivroPorSerial(String serial) {
        Optional<Livro> livro = livroRepository.findBySerial(serial);
        if (livro.isPresent()) {
            return livro.get();
        }
        throw new EntityNotFoundException("Ops! Não foi possível encontrar nenhum livro com esse código de barras");
    }

    public Livro cadastrarLivro(LivroCreationRequest livro) {
        Optional<Autor> autor = autorRepository.findById(livro.getAutorId());
        if (!autor.isPresent()) {
            throw new EntityNotFoundException("Ops! Não foi possível encontrar um Autor");
        }
        Livro livroToCreate = new Livro();
        BeanUtils.copyProperties(autor, livroToCreate);
        livroToCreate.setAutor(autor.get());
        return livroRepository.save(livroToCreate);
    }

    public void deletarLivro(Long id) {
        livroRepository.deleteById(id);
    }

    public Membro cadastrarMembro(MembroCreationRequest request) {
        Membro membro = new Membro();
        BeanUtils.copyProperties(request, membro);
        membro.setStatus(MembroStatus.ATIVO);
        return membroRepository.save(membro);
    }

    public Membro atualizarMembro (Long id, MembroCreationRequest request) {
        Optional<Membro> membroOpcional = membroRepository.findById(id);
        if (!membroOpcional.isPresent()) {
            throw new EntityNotFoundException("Não encontramos nenhum membro em nossa base de dados");
        }
        Membro membro = membroOpcional.get();
        membro.setSegundoNome(request.getSegundoNome());
        membro.setPrimeiroNome(request.getPrimeiroNome());
        return membroRepository.save(membro);
    }

    public Autor cadastrarAutor (AutorCreationRequest request) {
        Autor autor = new Autor();
        BeanUtils.copyProperties(request, autor);
        return autorRepository.save(autor);
    }

    public List<String> alugarUmLivro (LivroAluguelRequest request) {

        Optional<Membro> membroPorID = membroRepository.findById(request.getMembroId());
        if (!membroPorID.isPresent()) {
            throw new EntityNotFoundException("Membro não encontrado na base de dados");
        }

        Membro membro = membroPorID.get();
        if (membro.getStatus() != MembroStatus.ATIVO) {
            throw new RuntimeException("Usuário não está ativo.");
        }

        List<String> livrosDisponiveisParaLocar = new ArrayList<>();

        request.getLivroIds().forEach(livroId -> {

            Optional<Livro> livroPorId = livroRepository.findById(livroId);
            if (!livroPorId.isPresent()) {
                throw new EntityNotFoundException("Não encontramos nenhum livro com esse ID");
            }

            Optional<Aluguel> burrowedBook = aluguelRepository.findByLivroAndStatus(livroPorId.get(), AluguelStatus.INDISPONIVEL);
            if (!burrowedBook.isPresent()) {
                livrosDisponiveisParaLocar.add(livroPorId.get().getNome());
                Aluguel aluguel = new Aluguel();
                aluguel.setMembro(membroPorID.get());
                aluguel.setLivro(livroPorId.get());
                aluguel.setStatus(AluguelStatus.INDISPONIVEL);
                aluguel.setRetiradoEm(Instant.now());
                aluguel.setEntregueEm(Instant.now().plus(30, ChronoUnit.DAYS));
                aluguelRepository.save(aluguel);
            }

        });
        return livrosDisponiveisParaLocar;
    }


    public List<Autor> listarAutores() {
        return autorRepository.findAll();
    }

    public Livro atualizarLivro(Long livroId, LivroCreationRequest request) {
        Optional<Autor> autor = autorRepository.findById(request.getAutorId());
        if (!autor.isPresent()) {
            throw new EntityNotFoundException("Autor não encontrado");
        }
        Optional<Livro> livroOpcional = livroRepository.findById(livroId);
        if (!livroOpcional.isPresent()) {
            throw new EntityNotFoundException("Livro não encontrado");
        }
        Livro livro = livroOpcional.get();
        livro.setSerial(request.getSerial());
        livro.setNome(request.getNome());
        livro.setAutor(autor.get());
        return livroRepository.save(livro);
    }

    public List<Membro> listarMembros() {
        return membroRepository.findAll();
    }
}
