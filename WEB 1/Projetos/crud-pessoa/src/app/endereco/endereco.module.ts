import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListarEnderecoComponent } from './listar-endereco/listar-endereco.component';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { EnderecoService } from './services/endereco.service';
import { InserirEnderecoComponent } from './inserir-endereco/inserir-endereco.component';



@NgModule({
  declarations: [
    ListarEnderecoComponent,
    InserirEnderecoComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    FormsModule
  ],
  providers: [
    EnderecoService
  ]
})
export class EnderecoModule { }
