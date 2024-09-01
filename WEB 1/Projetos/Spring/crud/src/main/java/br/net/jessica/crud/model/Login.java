package br.net.jessica.crud.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class Login {
    @Setter @Getter
    private String login;

    @Setter @Getter
    private String senha;
}