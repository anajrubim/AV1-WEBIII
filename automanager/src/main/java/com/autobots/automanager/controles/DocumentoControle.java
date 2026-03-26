package com.autobots.automanager.controles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.autobots.automanager.entidades.Documento;
import com.autobots.automanager.modelo.DocumentoAtualizador;
import com.autobots.automanager.repositorios.DocumentoRepositorio;

@RestController
@RequestMapping("/documento")
public class DocumentoControle {

    @Autowired
    private DocumentoRepositorio repositorio;

    @GetMapping("/listar")
    public List<Documento> listar() {
        return repositorio.findAll();
    }

    @GetMapping("/buscar/{id}")
    public Documento buscar(@PathVariable long id) {
        return repositorio.findById(id).orElse(null);
    }

    @PostMapping("/cadastro")
    public void cadastrar(@RequestBody Documento documento) {
        repositorio.save(documento);
    }

    @PutMapping("/atualizar")
    public void atualizar(@RequestBody Documento atualizacao) {
        Documento documento = repositorio.getById(atualizacao.getId());

        DocumentoAtualizador atualizador = new DocumentoAtualizador();
        atualizador.atualizar(documento, atualizacao);

        repositorio.save(documento);
    }

    @DeleteMapping("/excluir/{id}")
    public void excluir(@PathVariable long id) {
        repositorio.deleteById(id);
    }
}