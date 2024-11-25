import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CompanyRoutingModule } from './company-routing.module';
import { CompanyComponent } from './company.component';
import { CompanyDashboardComponent } from './pages/company-dashboard/company-dashboard.component';
import { CreateHallComponent } from './pages/create-hall/create-hall.component';
import { ReactiveFormsModule } from '@angular/forms';
import { NgZorroModules } from '../NgZorroModules';
import { AllHallsComponent } from './pages/all-halls/all-halls.component';
import { UpdateHallComponent } from './pages/update-hall/update-hall.component';


@NgModule({
  declarations: [
    CompanyComponent,
    CompanyDashboardComponent,
    CreateHallComponent,
    AllHallsComponent,
    UpdateHallComponent
  ],
  imports: [
    CommonModule,
    CompanyRoutingModule,
    ReactiveFormsModule,
    NgZorroModules
  ]
})
export class CompanyModule { }
