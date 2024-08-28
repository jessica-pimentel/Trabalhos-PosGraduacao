import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListarPessoaComponent } from './pessoa/listar-pessoa/listar-pessoa.component';
import { InserirPessoaComponent } from './pessoa/inserir-pessoa/inserir-pessoa.component';
import { EditarPessoaComponent } from './pessoa/editar-pessoa/editar-pessoa.component';
import { ListarEnderecoComponent } from './endereco/listar-endereco/listar-endereco.component';
import { InserirEnderecoComponent } from './endereco/inserir-endereco/inserir-endereco.component';
import { EditarEnderecoComponent } from './endereco/editar-endereco/editar-endereco.component';
import { ListarCidadeComponent } from './cidade/listar-cidade/listar-cidade.component';
import { InserirCidadeComponent } from './cidade/inserir-cidade/inserir-cidade.component';
import { EditarCidadeComponent } from './cidade/editar-cidade/editar-cidade.component';
import { ListarEstadoComponent } from './estado/listar-estado/listar-estado.component';
import { InserirEstadoComponent } from './estado/inserir-estado/inserir-estado.component';
import { EditarEstadoComponent } from './estado/editar-estado/editar-estado.component';
import { LoginComponent } from './auth/login/login.component';
import { authGuard } from './auth/auth.guard';
import { HomeComponent } from './home/home.component';
import { ListarUsuarioComponent } from './usuario/listar-usuario/listar-usuario.component';
import { InserirEditarUsuarioComponent } from './usuario/inserir-editar-usuario/inserir-editar-usuario.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full'
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'home',
    component: HomeComponent,
    canActivate: [authGuard],
    data: {
      role: 'ADMIN, GERENTE, FUNC'
    }
  },
  {
    path: 'pessoas',
    redirectTo: 'pessoas/listar'
  },
  {
    path: 'pessoas/listar',
    component: ListarPessoaComponent,
    canActivate: [authGuard],
    data: {
      role: 'ADMIN, GERENTE, FUNC'
    }
  },
  {
    path: 'pessoas/novo',
    component: InserirPessoaComponent,
    canActivate: [authGuard],
    data: {
      role: 'ADMIN, GERENTE, FUNC'
    }
  },
  {
    path: 'pessoas/editar/:id',
    component: EditarPessoaComponent,
    canActivate: [authGuard],
    data: {
      role: 'ADMIN, GERENTE, FUNC'
    }
  },
  {
    path: 'enderecos',
    redirectTo: 'enderecos/listar'
  },
  {
    path: 'enderecos/listar',
    component: ListarEnderecoComponent,
    canActivate: [authGuard],
    data: {
      role: 'ADMIN, GERENTE'
    }
  },
  {
    path: 'enderecos/novo',
    component: InserirEnderecoComponent,
    canActivate: [authGuard],
    data: {
      role: 'ADMIN, GERENTE'
    }
  },
  {
    path: 'enderecos/editar/:id',
    component: EditarEnderecoComponent,
    canActivate: [authGuard],
    data: {
      role: 'ADMIN, GERENTE'
    }
  },
  {
    path: 'cidades',
    redirectTo: 'cidades/listar'
  },
  {
    path: 'cidades/listar',
    component: ListarCidadeComponent,
    canActivate: [authGuard],
    data: {
      role: 'ADMIN'
    }
  },
  {
    path: 'cidades/novo',
    component: InserirCidadeComponent,
    canActivate: [authGuard],
    data: {
      role: 'ADMIN'
    }
  },
  {
    path: 'cidades/editar/:id',
    component: EditarCidadeComponent,
    canActivate: [authGuard],
    data: {
      role: 'ADMIN'
    }
  },
  {
    path: 'estados',
    redirectTo: 'estados/listar'
  },
  {
    path: 'estados/listar',
    component: ListarEstadoComponent,
    canActivate: [authGuard],
    data: {
      role: 'ADMIN, FUNC'
    }
  },
  {
    path: 'estados/novo',
    component: InserirEstadoComponent,
    canActivate: [authGuard],
    data: {
      role: 'ADMIN, FUNC'
    }
  },
  {
    path: 'estados/editar/:id',
    component: EditarEstadoComponent,
    canActivate: [authGuard],
    data: {
      role: 'ADMIN, FUNC'
    }
  },
  {
    path: 'usuarios',
    redirectTo: 'usuarios/listar'
  },
  {
    path: 'usuarios/listar',
    component: ListarUsuarioComponent,
    canActivate: [authGuard],
    data: {
      role: 'ADMIN'
    }
  },
  {
    path: 'usuarios/novo',
    component: InserirEditarUsuarioComponent,
    canActivate: [authGuard],
    data: {
      role: 'ADMIN'
    }
  },
  {
    path: 'usuarios/editar/:id',
    component: InserirEditarUsuarioComponent,
    canActivate: [authGuard],
    data: {
      role: 'ADMIN'
    }
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
