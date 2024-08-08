import { Component, OnInit } from '@angular/core';
import { PessoaService } from '../services/pessoa.service';
import { Pessoa } from '../../shared/models/pessoa.model';

@Component({
  selector: 'app-listar-pessoa',
  templateUrl: './listar-pessoa.component.html',
  styleUrl: './listar-pessoa.component.css'
})
export class ListarPessoaComponent implements OnInit{
  pessoas : Pessoa[] = [];
  constructor(private pessoService : PessoaService) {}

  ngOnInit(): void {
    this.pessoas = this.listarTodos()
  }

  listarTodos(): Pessoa[] {
    return this.pessoService.listarTodos();
  }
}
