import { Component, OnInit } from '@angular/core';
import { ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Pessoa, Usuario } from '../../shared';
import { PessoaService } from '../services/pessoa.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-editar-pessoa',
  templateUrl: './editar-pessoa.component.html',
  styleUrl: './editar-pessoa.component.css'
})
export class EditarPessoaComponent implements OnInit {
  @ViewChild('formPessoa') formPessoa! : NgForm;
  pessoa : Pessoa = new Pessoa();
  mensagem: string = "";
  mensagem_detalhes: string = "";
  botaoDesabilitado = false;

  constructor (private pessoaService : PessoaService,
               private route : ActivatedRoute,
               private router: Router
  ) {}

  ngOnInit(): void {
    let id = +this.route.snapshot.params['id'];
    const res = this.pessoaService.buscarPorId(id).subscribe({
      next: (usuario) => {
        if (usuario == null) {
          this.mensagem = `Erro buscando usuário ${id}`;
          this.mensagem_detalhes = `Usuário não encontrado ${id}`;
          this.botaoDesabilitado = true;
        }
        else {
          this.pessoa = usuario;
          this.botaoDesabilitado = false;
        }
      },
      error: (err) => {
        this.mensagem = `Erro buscando usuário ${id}`;
        this.mensagem_detalhes = `[${err.status}] ${err.message}`;
      }
    });
  }

  atualizar() : void{
    if (this.formPessoa.form.valid){
      this.pessoaService.atualizar(this.pessoa).subscribe({
        next: (Usuario) => {
          this.router.navigate(["/pessoas"]);
        },
        error: (err) => {
          this.mensagem = `Erro alterando usuário ${this.pessoa.nome}`;
          this.mensagem_detalhes = `[${err.status}] ${err.message}`;
        }
      });
    } 
  }
}
