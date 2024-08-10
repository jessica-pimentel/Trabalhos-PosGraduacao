import { Component, ViewChild } from '@angular/core';
import { Curso } from '../../shared/models/curso.model';
import { NgForm } from '@angular/forms';
import { CursoService } from '../services/curso.service';
import { Router } from '@angular/router';
import { AlunoService } from '../../aluno/services/aluno.service';

@Component({
  selector: 'app-inserir-curso',
  templateUrl: './inserir-curso.component.html',
  styleUrl: './inserir-curso.component.css'
})
export class InserirCursoComponent {
 @ViewChild ('formCurso') formCurso! : NgForm;
 curso : Curso = new Curso();

 constructor (private cursoService : CursoService,
              private router : Router) { }

 inserir() : void {
  if (this.formCurso.form.valid){
    this.cursoService.inserir(this.curso);

    this.router.navigate(['/cursos']);
  }
 }
}
