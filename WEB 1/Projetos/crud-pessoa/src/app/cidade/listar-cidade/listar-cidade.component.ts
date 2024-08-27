import { Component, OnInit } from '@angular/core';
import { CidadeService } from '../services/cidade.service';
import { Cidade } from '../../shared';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ModalCidadeComponent } from '../modal-cidade/modal-cidade.component';

@Component({
  selector: 'app-listar-cidade',
  templateUrl: './listar-cidade.component.html',
  styleUrl: './listar-cidade.component.css'
})
export class ListarCidadeComponent implements OnInit {
  cidades : Cidade [] = [];

  constructor(private cidadeService : CidadeService,
              private modalService : NgbModal) {}

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

  abrirModalCidade(cidade: Cidade){
    const modalRef = this.modalService.open(ModalCidadeComponent);
    modalRef.componentInstance.cidade = cidade;
  }

}
