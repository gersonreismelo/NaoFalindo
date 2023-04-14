package br.com.fiap.naofalindo.config;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.naofalindo.models.Conta;
import br.com.fiap.naofalindo.models.Despesa;
import br.com.fiap.naofalindo.repository.ContaRepository;
import br.com.fiap.naofalindo.repository.DespesaRepository;

@Configuration
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    ContaRepository contaRepository;

    @Autowired
    DespesaRepository despesaRepository;

    @Override
    public void run(String... args) throws Exception {
        Conta c1 = new Conta(1L,"itau",new BigDecimal(100),"money");
        Conta c2 = new Conta(2L,"bradesco",new BigDecimal(0),"money");
        Conta c3 = new Conta(3L,"carteira",new BigDecimal(10),"coin");
        contaRepository.saveAll(List.of(c1,c2,c3));

        despesaRepository.saveAll(List.of(
            Despesa.builder().conta(c1).valor(new BigDecimal(100)).descricao("cinema").data(LocalDate.now()).build(),
            Despesa.builder().conta(c1).valor(new BigDecimal(45)).descricao("aluguel").data(LocalDate.now()).build(),
            Despesa.builder().conta(c1).valor(new BigDecimal(65)).descricao("netflix").data(LocalDate.now()).build(),
            Despesa.builder().conta(c1).valor(new BigDecimal(78)).descricao("internet").data(LocalDate.now()).build(),
            Despesa.builder().conta(c1).valor(new BigDecimal(12)).descricao("restaurante").data(LocalDate.now()).build(),
            Despesa.builder().conta(c1).valor(new BigDecimal(65)).descricao("estacionamento").data(LocalDate.now()).build(),
            Despesa.builder().conta(c1).valor(new BigDecimal(34)).descricao("imposto").data(LocalDate.now()).build(),
            Despesa.builder().conta(c1).valor(new BigDecimal(56)).descricao("tarifa").data(LocalDate.now()).build(),
            Despesa.builder().conta(c1).valor(new BigDecimal(9)).descricao("cinema").data(LocalDate.now()).build(),
            Despesa.builder().conta(c1).valor(new BigDecimal(5)).descricao("seguro").data(LocalDate.now()).build()
        ));
    }
}