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

import br.com.fiap.naofalindo.models.Conta;
import br.com.fiap.naofalindo.models.Despesa;
import br.com.fiap.naofalindo.repository.ContaRepository;
import jakarta.validation.Valid;


@RestController
@RequestMapping("api/conta")
public class ContaController {
    
    @Autowired
    ContaRepository contaRepository;

    
    Logger log = LoggerFactory.getLogger(Despesa.class);

    @GetMapping
    public List<Conta> index() {
        log.info("Buscando Lista de Contas");
        return contaRepository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> show(@PathVariable Long id) {
        log.info("Buscar Conta " + id);
        return ResponseEntity.ok(findByConta(id));
    }

    
    @PostMapping
    public ResponseEntity<Conta> create(@RequestBody @Valid Conta conta) {
        log.info("Cadastrando Conta" + conta);
        contaRepository.save(conta);
        return ResponseEntity.status(HttpStatus.CREATED).body(conta) ;
    }
    
    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        log.info("Deletando Conta");
        
        contaRepository.delete(findByConta(id));
        return ResponseEntity.noContent().build();
    }
    
    @PutMapping("{id}")
    public ResponseEntity<Object> update(@PathVariable @Valid Long id, @RequestBody Conta conta) {
        findByConta(id);
        
        conta.setId(id);
        contaRepository.save(conta);
        return ResponseEntity.ok(conta);
    }

    private Conta findByConta(Long id) {
        return contaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conta não encontrada"));
    }
}
