import { Component, OnInit } from '@angular/core';
import { Endereco } from '../../shared/models/endereco.model';
import { EnderecoService } from '../services/endereco.service';

@Component({
  selector: 'app-listar-endereco',
  templateUrl: './listar-endereco.component.html',
  styleUrl: './listar-endereco.component.css'
})
export class ListarEnderecoComponent implements OnInit {
  enderecos : Endereco [] = [] ;
  constructor(private enderecoService : EnderecoService) {

  }

  ngOnInit(): void {
    this.enderecos = this.listarTodos();
  }

  listarTodos(): Endereco [] {
    return this.enderecoService.listarTodos();
  }

  remover($event: any, endereco : Endereco) : void {
    $event.preventDefault();
    if (confirm(`Deseja realmente remover o endereço ${endereco.rua}?`)){
      this.enderecoService.remover(endereco.id!);
      this.enderecos = this.listarTodos();
    }
  }
}
