import { Component, OnInit } from '@angular/core';
import { Estado } from '../../shared/models/estado';
import { EstadoService } from '../services/estado.service';

@Component({
  selector: 'app-listar-estado',
  templateUrl: './listar-estado.component.html',
  styleUrl: './listar-estado.component.css'
})
export class ListarEstadoComponent implements OnInit {
  estados : Estado [] = [];
  constructor (private estadoService : EstadoService) {}

  ngOnInit(): void {
    this.estados = this.listarTodos();
  }

  listarTodos() : Estado [] {
    return this.estados = this.estadoService.listarTodos();
  }

  remover($event: any, estado : Estado) : void {
    $event.preventDefault();
    if (confirm(`Deseja realmente remover o estado ${estado.nome}?`)){
      this.estadoService.remover(estado.id!);
      this.estados = this.listarTodos();
    }
  }
}
