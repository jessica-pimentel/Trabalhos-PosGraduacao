import { Component, OnInit } from '@angular/core';
import { PessoaService } from '../services/pessoa.service';
import { Pessoa } from '../../shared';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ModalPessoaComponent } from '../modal-pessoa/modal-pessoa.component';

@Component({
  selector: 'app-listar-pessoa',
  templateUrl: './listar-pessoa.component.html',
  styleUrl: './listar-pessoa.component.css'
})
export class ListarPessoaComponent implements OnInit{
  pessoas : Pessoa[] = [];
  mensagem: string = "";
  mensagem_detalhes: string = "";
  constructor(private pessoService : PessoaService,
              private modalService: NgbModal) {}

  ngOnInit(): void {
    this.pessoas = this.listarTodos()
  }

  listarTodos(): Pessoa[] {
    this.pessoService.listarTodos().subscribe({
      next: (data: Pessoa[] | null) => {
        if (data == null) {
          this.pessoas = [];
        }
        else {
          this.pessoas = data;
        }
      },
      error: (err) => {
        this.mensagem = "Erro buscando lista de pessoas";
        this.mensagem_detalhes = `[${err.status}] ${err.message}`;
      }
    });
    return this.pessoas;
  }

  remover($event: any, pessoa : Pessoa) : void {
    $event.preventDefault();
    if (confirm(`Deseja realmente remover a pessoa ${pessoa.nome}?`)){
      this.pessoService.remover(pessoa.id!).subscribe({
        complete: () => {this.listarTodos(); },
        error: (err) => {
          this.mensagem = `Erro removendo usuário ${pessoa.id} - ${pessoa.nome}`;
          this.mensagem_detalhes = `[${err.status}] ${err.message}`;
        }
      });
      this.pessoas = this.listarTodos();
    }
  }

  abrirModalPessoa(pessoa: Pessoa){
    const modalRef = this.modalService.open(ModalPessoaComponent);
    modalRef.componentInstance.pessoa = pessoa;
  }
}
