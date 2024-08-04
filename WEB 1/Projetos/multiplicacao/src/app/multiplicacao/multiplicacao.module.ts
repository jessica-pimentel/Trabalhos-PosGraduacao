import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MultiplicacaoComponent } from './multiplicacao';
import { MultiplicacaoService } from './services';



@NgModule({
  declarations: [
    MultiplicacaoComponent
  ],
  imports: [
    CommonModule
  ],
  exports: [
    MultiplicacaoComponent
  ],
  providers:[
    MultiplicacaoService
  ]
})
export class MultiplicacaoModule { }
