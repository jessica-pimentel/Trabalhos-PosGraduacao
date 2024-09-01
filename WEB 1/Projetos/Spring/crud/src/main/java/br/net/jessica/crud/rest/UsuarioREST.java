package br.net.jessica.crud.rest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import br.net.jessica.crud.model.Login;
import br.net.jessica.crud.model.Pessoa;
import br.net.jessica.crud.model.Usuario;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@CrossOrigin
@RestController
public class UsuarioREST {
    public static List<Usuario> lista = new ArrayList<>();
    public static List<Pessoa> pessoas = new ArrayList<>();

    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> obterTodosUsuarios () {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        // return ResponseEntity.ok(lista);
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> obterUsuarioPorId(@PathVariable("id") int id) {
        Usuario u = lista.stream().filter(
            usu -> usu.getId() == id).findAny().orElse(null);
        if (u==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        else
            return ResponseEntity.ok(u);
    }
    
    @PostMapping("/usuarios")
    public ResponseEntity<Usuario> inserir(@RequestBody Usuario usuario) {
        Usuario u = lista.stream().filter(
            usu -> usu.getLogin().equals(usuario.getLogin())).findAny().orElse(null);
        if (u != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        u = lista.stream().max(Comparator.comparing(Usuario::getId)).orElse(null);
        if (u == null)
            usuario.setId(1);
        else 
            usuario.setId(u.getId() + 1);
        lista.add(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @PutMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> alterar (@PathVariable("id") int id, @RequestBody Usuario usuario) {
        Usuario u = lista.stream().filter(
            usu -> usu.getId() == id).findAny().orElse(null);
        if (u != null) {
            u.setNome(usuario.getNome());
            u.setLogin(usuario.getLogin());
            u.setSenha(usuario.getSenha());
            u.setPerfil(usuario.getPerfil());
            return ResponseEntity.ok(u);
        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> remover(@PathVariable("id") int id) {
        Usuario usuario = lista.stream().filter(
            usu -> usu.getId() == id).findAny().orElse(null);
        if (usuario != null) {
            lista.removeIf(u -> u.getId() == id);
            return ResponseEntity.ok(usuario);
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Usuario> login (@RequestBody Login login) {
        Usuario usuario = lista.stream().filter(
            usu -> usu.getLogin().equals(login.getLogin()) && 
                   usu.getSenha().equals(login.getSenha())).findAny().orElse(null);
        if (usuario == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        else
            return ResponseEntity.ok(usuario);
    }
      
    @GetMapping("/pessoas")
    public ResponseEntity<List<Pessoa>> obterTodasPessoas () {
        return ResponseEntity.ok(pessoas);
    }
    
    @GetMapping("/pessoas/{id}")
    public ResponseEntity<Pessoa> obterPessoaPorId (@PathVariable("id") int id) {
        Pessoa p = pessoas.stream().filter(pess -> pess.getId() == id).findAny().orElse(null);
        if (p == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); 
        else
            return ResponseEntity.ok(p);
        }

        @PostMapping("/pessoas")
        public ResponseEntity<Pessoa> inserirPessoa(@RequestBody Pessoa pessoa) {
            Pessoa p = pessoas.stream().filter(
                pess -> pess.getNome().equals(pessoa.getNome())).findAny().orElse(null);
            if (p != null) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
            p = pessoas.stream().max(Comparator.comparing(Pessoa::getId)).orElse(null);
            if (p == null)
                pessoa.setId(1);
            else 
                pessoa.setId(p.getId() + 1);
            pessoas.add(pessoa);
            return ResponseEntity.status(HttpStatus.CREATED).body(pessoa);
        }
    
        @PutMapping("/pessoas/{id}")
        public ResponseEntity<Pessoa> alterarPessoa (@PathVariable("id") int id, @RequestBody Pessoa pessoa) {
            Pessoa p = pessoas.stream().filter(
                pess -> pess.getId() == id).findAny().orElse(null);
            if (p != null) {
                p.setNome(pessoa.getNome());
                p.setIdade(pessoa.getIdade());
                p.setDataNascimento(pessoa.getDataNascimento());
                p.setMotorista(pessoa.getMotorista());
                return ResponseEntity.ok(p);
            }
            else
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    
        @DeleteMapping("/pessoas/{id}")
        public ResponseEntity<Pessoa> removerPessoa(@PathVariable("id") int id) {
            Pessoa pessoa = pessoas.stream().filter(
                pess -> pess.getId() == id).findAny().orElse(null);
            if (pessoa != null) {
                pessoas.removeIf(p -> p.getId() == id);
                return ResponseEntity.ok(pessoa);
            }
            else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        }
        
    static {
        lista.add(
            new Usuario(1, "administr", "admin", "admin", "ADMIN"));
        lista.add(
            new Usuario(2, "gerent", "gerente", "gerente", "GERENTE"));
        lista.add(
            new Usuario(3, "funcion", "func", "func", "FUNC"));
    }
}
