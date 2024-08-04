import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SubtracaoComponent } from './subtracao';
import { SubtracaoService } from './services';



@NgModule({
  declarations: [
    SubtracaoComponent
  ],
  imports: [
    CommonModule
  ],
  exports: [
    SubtracaoComponent
  ],
  providers:[
    SubtracaoService
  ]
})
export class SubtracaoModule { }
