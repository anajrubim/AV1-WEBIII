package com.autobots.automanager.controles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.autobots.automanager.entidades.Endereco;
import com.autobots.automanager.modelo.EnderecoAtualizador;
import com.autobots.automanager.repositorios.EnderecoRepositorio;

@RestController
@RequestMapping("/endereco")
public class EnderecoControle {

    @Autowired
    private EnderecoRepositorio repositorio;

    @GetMapping("/listar")
    public List<Endereco> listar() {
        return repositorio.findAll();
    }

    @GetMapping("/buscar/{id}")
    public Endereco buscar(@PathVariable long id) {
        return repositorio.findById(id).orElse(null);
    }

    @PostMapping("/cadastro")
    public void cadastrar(@RequestBody Endereco endereco) {
        repositorio.save(endereco);
    }

    @PutMapping("/atualizar")
    public void atualizar(@RequestBody Endereco atualizacao) {
        Endereco endereco = repositorio.getById(atualizacao.getId());

        EnderecoAtualizador atualizador = new EnderecoAtualizador();
        atualizador.atualizar(endereco, atualizacao);

        repositorio.save(endereco);
    }

    @DeleteMapping("/excluir/{id}")
    public void excluir(@PathVariable long id) {
        repositorio.deleteById(id);
    }
}