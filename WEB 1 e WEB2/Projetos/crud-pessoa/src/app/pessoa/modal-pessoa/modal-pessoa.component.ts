import { Component, Input } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Pessoa } from '../../shared';

@Component({
  selector: 'app-modal-pessoa',
  templateUrl: './modal-pessoa.component.html',
  styleUrl: './modal-pessoa.component.css'
})
export class ModalPessoaComponent {
  @Input() pessoa! : Pessoa;

  constructor(public activeModal: NgbActiveModal) {}
}
