package br.net.jessica.crud.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import br.net.jessica.crud.model.Usuario;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@CrossOrigin
@RestController
public class UsuarioREST {
    public static List<Usuario> lista = new ArrayList<>();

    @GetMapping("/usuarios")
    public List<Usuario> obterTodosUsuarios () {
        return lista;
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
