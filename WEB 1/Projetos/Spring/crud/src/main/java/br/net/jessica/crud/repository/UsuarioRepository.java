package br.net.jessica.crud.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.net.jessica.crud.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
    
    public Optional<Usuario> findByLogin(String login);
    
    public Optional<Usuario> findByLoginAndSenha(String login, String senha);
}
