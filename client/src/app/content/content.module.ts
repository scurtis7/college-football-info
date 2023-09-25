import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ContentAppComponent } from './content-app.component';
import { HeaderComponent } from './components/header/header.component';
import { SidenavComponent } from './components/sidenav/sidenav.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { MaterialModule } from "../shared/material.module";
import { FormsModule } from "@angular/forms";
import { RouterModule, Routes } from "@angular/router";
import { TeamsComponent } from './components/teams/teams.component';
import { RatingsComponent } from './components/ratings/ratings.component';

const routes: Routes = [
  {
    path: '', component: ContentAppComponent,
    children: [
      { path: '', component: DashboardComponent },
      { path: 'teams', component: TeamsComponent },
      { path: 'ratings', component: RatingsComponent },
    ]
  },
  { path: '**', redirectTo: '' }
];

@NgModule({
  declarations: [
    ContentAppComponent,
    HeaderComponent,
    SidenavComponent,
    DashboardComponent,
    TeamsComponent,
    RatingsComponent
  ],
  imports: [
    CommonModule,
    MaterialModule,
    FormsModule,
    RouterModule.forChild(routes)
  ]
})
export class ContentModule { }
