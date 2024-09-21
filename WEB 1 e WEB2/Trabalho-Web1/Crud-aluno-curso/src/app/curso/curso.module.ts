import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListarCursoComponent } from './listar-curso/listar-curso.component';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CursoService } from './services/curso.service';
import { InserirCursoComponent } from './inserir-curso/inserir-curso.component';
import { EditarCursoComponent } from './editar-curso/editar-curso.component';



@NgModule({
  declarations: [
    ListarCursoComponent,
    InserirCursoComponent,
    EditarCursoComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    FormsModule
  ],
  providers: [
    CursoService
  ]
})
export class CursoModule { }
