import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { SubtracaoModule } from './subtracao';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    SubtracaoModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
