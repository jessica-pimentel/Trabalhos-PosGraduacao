import { Injectable } from '@angular/core';

import { Pessoa } from '../../shared/models/pessoa.model'; 

const LS_CHAVE = "pessoas";

@Injectable({
  providedIn: 'root'
})
export class PessoaService {

  constructor() { }

  listarTodos() : Pessoa[]{
    let pessoas = localStorage[LS_CHAVE];
    return pessoas ? JSON.parse(pessoas) : [];
  }

  inserir(pessoa : Pessoa) : void {
    let pessoas = this.listarTodos();
    pessoa.id = new Date().getTime();
    pessoas.push(pessoa);
    localStorage[LS_CHAVE] = JSON.stringify(pessoas);
  }

  buscarPorId(id : number) : Pessoa | undefined {
    let pessoas = this.listarTodos();
    return pessoas.find(pessoa => pessoa.id === id);
  }

  atualizar(pessoa : Pessoa) : void {
    let pessoas = this.listarTodos();
    pessoas.forEach((obj, index, objs) => {
      if (pessoa.id === obj.id) {
        objs[index] = pessoa;
      }
    });   
    localStorage[LS_CHAVE] = JSON.stringify(pessoas);
  }

  remover(id : number) : void {
    let pessoas = this.listarTodos();
    pessoas = pessoas.filter(pessoa => pessoa.id !== id);
    localStorage[LS_CHAVE] = JSON.stringify(pessoas);
  }
}
