import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { RouterModule, Routes } from "@angular/router";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from "./shared/material.module";
import { FormsModule } from "@angular/forms";

const routes: Routes = [
  { path: 'content', loadChildren: () => import('./content/content.module').then(m => m.ContentModule) },
  { path: '**', redirectTo: 'content' }
];

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MaterialModule,
    FormsModule,
    RouterModule.forRoot(routes),
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
