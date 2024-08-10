import { Component, OnInit } from '@angular/core';
import { CidadeService } from '../services/cidade.service';
import { Cidade } from '../../shared/models/cidade';

@Component({
  selector: 'app-listar-cidade',
  templateUrl: './listar-cidade.component.html',
  styleUrl: './listar-cidade.component.css'
})
export class ListarCidadeComponent implements OnInit {
  cidades : Cidade [] = [];

  constructor(private cidadeService : CidadeService) {}

  ngOnInit(): void {
    this.cidades = this.listarTodos();
  }

  listarTodos() : Cidade [] {
    return this.cidadeService.listarTodos();
  }

  remover($event: any, cidade : Cidade) : void {
    $event.preventDefault();
    if (confirm(`Deseja realmente remover o endereço ${cidade.nome}?`)){
      this.cidadeService.remover(cidade.id!);
      this.cidades = this.listarTodos();
    }
  }

}
