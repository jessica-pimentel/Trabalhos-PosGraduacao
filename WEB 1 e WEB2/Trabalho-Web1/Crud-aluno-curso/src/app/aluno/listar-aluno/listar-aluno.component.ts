import { Component, OnInit } from '@angular/core';
import { Aluno } from '../../shared/models/aluno.model';
import { AlunoService } from '../services/aluno.service';

@Component({
  selector: 'app-listar-aluno',
  templateUrl: './listar-aluno.component.html',
  styleUrl: './listar-aluno.component.css'
})
export class ListarAlunoComponent implements OnInit {
  alunos : Aluno [] = [];
  constructor (private alunoService : AlunoService) { }

  ngOnInit(): void {
    this.alunos = this.listarTodos();
  }

  listarTodos() : Aluno [] {
    return this.alunoService.listarTodos();
  }

  remover($event: any, aluno : Aluno) : void {
    $event.preventDefault();
    if (confirm(`Deseja realmente remover o aluno ${aluno.nome}?`)){
      this.alunoService.remover(aluno.id!);
      this.alunos = this.listarTodos();
    }
  }
}
