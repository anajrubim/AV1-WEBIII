package com.autobots.automanager.controles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.autobots.automanager.entidades.Telefone;
import com.autobots.automanager.modelo.TelefoneAtualizador;
import com.autobots.automanager.repositorios.TelefoneRepositorio;

@RestController
@RequestMapping("/telefone")
public class TelefoneControle {

    @Autowired
    private TelefoneRepositorio repositorio;

    @GetMapping("/listar")
    public List<Telefone> listar() {
        return repositorio.findAll();
    }

    @GetMapping("/buscar/{id}")
    public Telefone buscar(@PathVariable long id) {
        return repositorio.findById(id).orElse(null);
    }

    @PostMapping("/cadastro")
    public void cadastrar(@RequestBody Telefone telefone) {
        repositorio.save(telefone);
    }

    @PutMapping("/atualizar")
    public void atualizar(@RequestBody Telefone atualizacao) {
        Telefone telefone = repositorio.getById(atualizacao.getId());

        TelefoneAtualizador atualizador = new TelefoneAtualizador();
        atualizador.atualizar(telefone, atualizacao);

        repositorio.save(telefone);
    }

    @DeleteMapping("/excluir/{id}")
    public void excluir(@PathVariable long id) {
        repositorio.deleteById(id);
    }
}