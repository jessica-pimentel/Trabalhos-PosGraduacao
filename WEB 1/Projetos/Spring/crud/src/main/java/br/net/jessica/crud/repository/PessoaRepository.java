package br.net.jessica.crud.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.net.jessica.crud.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer>{
    Optional<Pessoa> findByNome(String nome);
}
