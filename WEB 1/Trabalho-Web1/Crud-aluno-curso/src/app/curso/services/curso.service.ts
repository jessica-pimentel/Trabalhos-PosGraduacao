import { Injectable } from '@angular/core';
import { Curso } from '../../shared/models/curso.model';

const [LS_CHAVE] = ('cursos');

@Injectable({
  providedIn: 'root'
})
export class CursoService {

  constructor() { }

  listarTodos() : Curso [] {
    let cursos = localStorage[LS_CHAVE];
    return cursos ? JSON.parse(cursos) : [];
  }

  inserir(curso : Curso) : void {
    let cursos = this.listarTodos();
    curso.id = new Date().getTime();
    cursos.push(curso);
    localStorage[LS_CHAVE] = JSON.stringify(cursos);
  }

  buscarPorId(id : number) :  Curso | undefined {
    let cursos = this.listarTodos();
    return cursos.find(curso => curso.id === id);
  }

  atualizar(curso : Curso) : void{
    let cursos = this.listarTodos();
    cursos.forEach((obj, index, objs) =>{
      if (curso.id == obj.id) {
        objs[index] = curso;
      }
    });
    localStorage[LS_CHAVE] = JSON.stringify(cursos);
  }

  remover(id : number) : void {
    let cursos = this.listarTodos();
    cursos = cursos.filter(curso => curso.id !== id)
    localStorage[LS_CHAVE] = JSON.stringify(cursos);
  }
}
