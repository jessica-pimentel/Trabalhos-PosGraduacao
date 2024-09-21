import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { ListarAlunoComponent } from './listar-aluno/listar-aluno.component';
import { AlunoService } from './services/aluno.service';
import { InserirAlunoComponent } from './inserir-aluno/inserir-aluno.component';
import { EditarAlunoComponent } from './editar-aluno/editar-aluno.component';



@NgModule({
  declarations: [
    ListarAlunoComponent,
    InserirAlunoComponent,
    EditarAlunoComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    FormsModule
  ],
  providers: [
    AlunoService
  ]
})
export class AlunoModule { }
