import { Component, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Aluno } from '../../shared/models/aluno.model';
import { AlunoService } from '../services/aluno.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-inserir-aluno',
  templateUrl: './inserir-aluno.component.html',
  styleUrl: './inserir-aluno.component.css'
})
export class InserirAlunoComponent {
  @ViewChild ('formAluno') formAluno! : NgForm;
  aluno : Aluno = new Aluno();

  constructor (private alunoService : AlunoService,
               private router : Router) { }
  
  inserir() : void {
    if (this.formAluno.form.valid) {
      this.alunoService.inserir(this.aluno);

      this.router.navigate(['/alunos']);
    }
  }
}
