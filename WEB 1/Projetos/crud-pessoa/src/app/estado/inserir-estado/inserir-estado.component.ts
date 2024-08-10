import { Component, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Estado } from '../../shared/models/estado';
import { EstadoService } from '../services/estado.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-inserir-estado',
  templateUrl: './inserir-estado.component.html',
  styleUrl: './inserir-estado.component.css'
})
export class InserirEstadoComponent {
  @ViewChild('formEstado') formEstado! : NgForm;
  estado : Estado = new Estado();

constructor (private estadoService : EstadoService,
    private router : Router) {}

inserir() : void{
if (this.formEstado.form.valid){
this.estadoService.inserir(this.estado);
   
this.router.navigate(['/estados']);
}
}            

}
