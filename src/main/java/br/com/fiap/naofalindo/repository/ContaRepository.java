package br.com.fiap.naofalindo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.naofalindo.models.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long> {
}