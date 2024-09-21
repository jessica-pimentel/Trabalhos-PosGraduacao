package br.net.jessica.crud.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;

@Entity 
@Table(name="tb_usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_usu")
    @Setter @Getter
    private int id;

    @Column(name="nome_usu")
    @Setter @Getter
    private String nome;

    @Column(name="login_usu")
    @Setter @Getter
    private String login;

    @Column(name="senha_usu")
    @Setter @Getter
    private String senha;

    @Column(name="perfil_usu")
    @Setter @Getter
    private String perfil;

    private Usuario() {}
    public Usuario(int id, String nome, String login, String senha, String perfil) {
        this.id = id;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.perfil = perfil;
    }
}
