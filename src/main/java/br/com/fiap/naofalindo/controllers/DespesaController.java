package br.com.fiap.naofalindo.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.naofalindo.models.Despesa;
import br.com.fiap.naofalindo.repository.DespesaRepository;
import jakarta.validation.Valid;


@RestController
@RequestMapping("api/despesas")
public class DespesaController {
    
    @Autowired
    DespesaRepository repository;
    
    Logger log = LoggerFactory.getLogger(Despesa.class);

    @GetMapping
    public List<Despesa> index() {
        log.info("Buscando Lista de Despesas ");
        return repository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> show(@PathVariable Long id) {
        log.info("Buscar Despesa " + id);
        var despesaEncontrada = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Despesa não encontrada"));
        return ResponseEntity.ok(despesaEncontrada);
    }

    @PostMapping
    public ResponseEntity<Despesa> create(@RequestBody @Valid Despesa despesa) {
        log.info("Cadastrando Despesa" + despesa);
        repository.save(despesa);
        return ResponseEntity.status(HttpStatus.CREATED).body(despesa) ;
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        log.info("Deletando Despesa");
        var despesaEncontrada = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Erro ao deletar, despesa não encontrada"));

        repository.delete(despesaEncontrada);
        return ResponseEntity.noContent().build();
    }
    
    @PutMapping("{id}")
    public ResponseEntity<Object> update(@PathVariable @Valid Long id, @RequestBody Despesa despesa) {
        repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Erro ao atualizar, despesa não encontrada"));
        
        despesa.setId(id);
        repository.save(despesa);
        return ResponseEntity.ok(despesa);
    }
}
