import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DivisaoComponent } from './divisao';
import { DivisaoService } from './services';



@NgModule({
  declarations: [
    DivisaoComponent
  ],
  imports: [
    CommonModule
  ],
  exports: [
    DivisaoComponent
  ],
  providers: [
    DivisaoService
  ]
})
export class DivisaoModule { }
