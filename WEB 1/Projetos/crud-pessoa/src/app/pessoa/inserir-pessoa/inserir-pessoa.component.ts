import { Component} from '@angular/core';
import { ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Pessoa } from '../../shared';
import { PessoaService } from '../services/pessoa.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-inserir-pessoa',
  templateUrl: './inserir-pessoa.component.html',
  styleUrl: './inserir-pessoa.component.css'
})
export class InserirPessoaComponent {
  @ViewChild('formPessoa') formPessoa! : NgForm;
  pessoa : Pessoa = new Pessoa();
  mensagem: string = "";
  mensagem_detalhes: string = "";

  constructor (private pessoaService : PessoaService, 
               private router : Router) {}

  inserir() : void{
    if (this.formPessoa.form.valid){
      this.pessoaService.inserir(this.pessoa).subscribe({
        next: (usuario) => {
          this.router.navigate(["/pessoas"]);
        },
        error: (err) => {
          this.mensagem = `Erro inserindo usuário ${this.pessoa.nome}`;
          if (err.status == 409) {
            this.mensagem_detalhes = "Usuario já existente.";
          }
          else {
            this.mensagem_detalhes = `[${err.status}] ${err.message}`;
          }
        }
      });
      this.router.navigate(["/pessoas"]);
    }
  }
}


