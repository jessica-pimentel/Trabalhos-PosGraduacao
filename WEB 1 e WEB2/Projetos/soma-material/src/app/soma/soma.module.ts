import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SomaComponent } from './soma';
import { SomaService } from './services';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';



@NgModule({
  declarations: [
    SomaComponent
  ],
  imports: [
    CommonModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    MatButtonModule
  ],
  exports: [
    SomaComponent
  ],
  providers: [
    SomaService
  ]
})
export class SomaModule { }
