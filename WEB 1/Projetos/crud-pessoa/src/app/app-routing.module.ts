import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListarPessoaComponent } from './pessoa/listar-pessoa/listar-pessoa.component';
import { InserirPessoaComponent } from './pessoa/inserir-pessoa/inserir-pessoa.component';
import { EditarPessoaComponent } from './pessoa/editar-pessoa/editar-pessoa.component';
import { ListarEnderecoComponent } from './endereco/listar-endereco/listar-endereco.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'pessoas/listar',
    pathMatch: 'full'
  },
  {
    path: 'pessoas',
    redirectTo: 'pessoas/listar'
  },
  {
    path: 'pessoas/listar',
    component: ListarPessoaComponent
  },
  {
    path: 'pessoas/novo',
    component: InserirPessoaComponent
  },
  {
    path: 'pessoas/editar/:id',
    component: EditarPessoaComponent
  },
  {
    path: 'enderecos',
    redirectTo: 'enderecos/listar'
  },
  {
    path: 'enderecos/listar',
    component: ListarEnderecoComponent
  // },
  // {
  //   path: 'enderecos/novo',
  //   component: InserirEnderecoComponent
   }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
