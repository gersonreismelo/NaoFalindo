package br.com.fiap.naofalindo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.naofalindo.models.Despesa;
import br.com.fiap.naofalindo.repository.ContaRepository;
import br.com.fiap.naofalindo.repository.DespesaRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("api/despesas")
@Slf4j
public class DespesaController {
    
    @Autowired
    DespesaRepository despesaRepository;

    @Autowired
    ContaRepository contaRepository;

    @GetMapping
    public Page<Despesa> index(@RequestParam(required = false) String busca, @PageableDefault(size = 5) Pageable pageable){        
        // Pageable pageable = Pageable.ofSize(tamanho).withPage(pagina);
        if (busca == null) 
            return despesaRepository.findAll(pageable);
        return despesaRepository.findByDescricaoContaining(busca, pageable);
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> show(@PathVariable Long id) {
        log.info("Buscar Despesa " + id);
        return ResponseEntity.ok(findByDespesa(id));
    }

    
    @PostMapping
    public ResponseEntity<Despesa> create(@RequestBody @Valid Despesa despesa) {
        log.info("Cadastrando Despesa" + despesa);
        despesaRepository.save(despesa);
        despesa.setConta(contaRepository.findById(despesa.getConta().getId()).get());
        return ResponseEntity.status(HttpStatus.CREATED).body(despesa) ;
    }
    
    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        log.info("Deletando Despesa");
        
        despesaRepository.delete(findByDespesa(id));
        return ResponseEntity.noContent().build();
    }
    
    @PutMapping("{id}")
    public ResponseEntity<Object> update(@PathVariable @Valid Long id, @RequestBody Despesa despesa) {
        findByDespesa(id);
        
        despesa.setId(id);
        despesaRepository.save(despesa);
        return ResponseEntity.ok(despesa);
    }

    private Despesa findByDespesa(Long id) {
        return despesaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Despesa não encontrada"));
    }
}
