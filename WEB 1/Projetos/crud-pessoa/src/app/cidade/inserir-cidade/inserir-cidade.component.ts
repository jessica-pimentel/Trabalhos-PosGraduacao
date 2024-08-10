import { Component, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Cidade } from '../../shared/models/cidade';
import { CidadeService } from '../services/cidade.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-inserir-cidade',
  templateUrl: './inserir-cidade.component.html',
  styleUrl: './inserir-cidade.component.css'
})
export class InserirCidadeComponent {
  @ViewChild('formCidade') formCidade! : NgForm;
  cidade : Cidade = new Cidade();

  constructor (private cidadeService : CidadeService,
               private router : Router) {}

  inserir() : void{
  if (this.formCidade.form.valid){
    this.cidadeService.inserir(this.cidade);
              
    this.router.navigate(['/cidades']);
    }
  }             
}
