import { Component } from '@angular/core';
import { DivisaoService } from '../services';

@Component({
  selector: 'app-divisao',
  templateUrl: './divisao.component.html',
  styleUrl: './divisao.component.css'
})
export class DivisaoComponent {
  private res : number = 0;
  constructor(private divisaoService : DivisaoService){
  }

  divisaoBotao(numero1 : string, numero2: string): void{
    let n1: number;
    let n2: number;

    n1 = parseFloat(numero1);
    n2 = parseFloat(numero2);

    this.res = this.divisaoService.divisaoBotao(n1, n2);
  }

  get resultado(): string{
    return this.res.toString();
  }
}
