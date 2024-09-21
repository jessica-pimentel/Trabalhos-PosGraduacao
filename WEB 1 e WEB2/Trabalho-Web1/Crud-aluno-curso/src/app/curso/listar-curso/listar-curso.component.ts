import { Component, OnInit } from '@angular/core';
import { Curso } from '../../shared/models/curso.model';
import { CursoService } from '../services/curso.service';

@Component({
  selector: 'app-listar-curso',
  templateUrl: './listar-curso.component.html',
  styleUrl: './listar-curso.component.css'
})
export class ListarCursoComponent implements OnInit {
  cursos : Curso [] = [];

  constructor (private cursoService : CursoService) {}

  ngOnInit(): void {
    this.cursos = this.listarTodos();
  }

  listarTodos() : Curso [] {
    return this.cursoService.listarTodos();
  }

  remover($event: any, curso : Curso) : void {
    $event.preventDefault();
    if (confirm(`Deseja realmente remover o curso ${curso.nome}?`)){
      this.cursoService.remover(curso.id!);
      this.cursos = this.listarTodos();
    }
  }
}
