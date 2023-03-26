package br.com.fiap.naofalindo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.naofalindo.models.Despesa;

public interface DespesaRepository extends JpaRepository<Despesa, Long> {
    
}
