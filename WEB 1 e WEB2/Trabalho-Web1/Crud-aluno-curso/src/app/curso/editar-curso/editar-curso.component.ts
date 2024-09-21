import { Component, OnInit, ViewChild, viewChild } from '@angular/core';
import { Curso } from '../../shared/models/curso.model';
import { CursoService } from '../services/curso.service';
import { ActivatedRoute, Router } from '@angular/router';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-editar-curso',
  templateUrl: './editar-curso.component.html',
  styleUrl: './editar-curso.component.css'
})
export class EditarCursoComponent implements OnInit{
  @ViewChild('formCurso') formCurso! : NgForm;
  curso : Curso = new Curso();

  constructor (private cursoService : CursoService,
               private route : ActivatedRoute,
               private router : Router) { }

  ngOnInit(): void {
    let id = +this.route.snapshot.params['id'];
    const res = this.cursoService.buscarPorId(id);
    if (res !== undefined)
      this.curso = res;
    else
      throw new Error ("Curso não encontrado: id = " + id);
  }

  atualizar() : void {
    if (this.formCurso.form.valid){
      this.cursoService.atualizar(this.curso);
      this.router.navigate(['/cursos']);
    }
  }
}
